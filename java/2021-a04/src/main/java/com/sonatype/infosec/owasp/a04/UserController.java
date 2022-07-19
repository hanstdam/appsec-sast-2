package com.sonatype.infosec.owasp.a04;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a04.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a04.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a04.models.Option;
import com.sonatype.infosec.owasp.a04.models.User;
import com.sonatype.infosec.owasp.a04.repositories.UserRepository;

@RestController
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> findUser(HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {
		UserRepository userRepository = new UserRepository();

		String idAsString = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idAsString);
		} catch (NumberFormatException e) {
			// CWE-209: Generation of Error Message Containing Sensitive Information
			e.printStackTrace(response.getWriter());
		}

		Option<User> userOption = userRepository.getUser(id);
		if (userOption.hasValue()) {
			return new ResponseEntity<User>(userOption.getValue(), HttpStatus.OK);
		}

		throw new NotFoundException(ApiErrorCode.USER_NOT_FOUND, "Could not find user with provided id");
	}
}
