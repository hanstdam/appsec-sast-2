package com.sonatype.infosec.owasp.a09;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a09.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a09.exceptions.BadRequestException;
import com.sonatype.infosec.owasp.a09.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a09.models.Option;
import com.sonatype.infosec.owasp.a09.models.User;
import com.sonatype.infosec.owasp.a09.repositories.UserRepository;

@RestController
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> getUser(HttpServletRequest request) throws BadRequestException, NotFoundException {
		UserRepository userRepository = new UserRepository();

		String idAsString = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idAsString);
		} catch (NumberFormatException e) {
			// CWE-117: Improper Output Neutralization for Logs
			logger.info("Failed to parse id = {}", idAsString);
			throw new BadRequestException(ApiErrorCode.USER_CONTROLLER_FAILED_TO_PARSE_USER_ID, "Unable to parse user id");
		}
		
		Option<User> userOption = userRepository.getUser(id);
		if (userOption.hasValue()) {
			return new ResponseEntity<User>(userOption.getValue(), HttpStatus.OK);
		}

		throw new NotFoundException(ApiErrorCode.USER_NOT_FOUND, "Could not find user with provided id");
	}
}
