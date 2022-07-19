package com.sonatype.infosec.owasp.a08;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a08.models.Index;

@RestController
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Index> index() {
		return new ResponseEntity<Index>(
			new Index("OWASP Top 10", 2021, "A08", "Software and Data Integrity Failures"),
			HttpStatus.OK
		);
	}
}
