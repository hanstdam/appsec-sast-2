package com.sonatype.infosec.owasp.a10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a10.models.ServiceValidationResponse;

@RestController
public class ServiceController {
	
	@RequestMapping(value = "/service/validate/iq-server", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ServiceValidationResponse> validateIfServiceIsIqServer(HttpServletRequest request) throws Exception {
		
		String serviceUrl = request.getParameter("url");
		try {
			URL url = new URL(serviceUrl);
			// CWE-918: Server-Side Request Forgery (SSRF)
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String inputLine;
			while ((inputLine = buffer.readLine()) != null) {
				if (inputLine.contains("IQ Server")) {
					return new ResponseEntity<ServiceValidationResponse>(new ServiceValidationResponse(serviceUrl, true), HttpStatus.OK);
				}
			}
			buffer.close();
		} catch (Exception e) {
			// Ignore. Will return as not an IQ Server later
		}
		return new ResponseEntity<ServiceValidationResponse>(new ServiceValidationResponse(serviceUrl, false), HttpStatus.OK);
	}
}
