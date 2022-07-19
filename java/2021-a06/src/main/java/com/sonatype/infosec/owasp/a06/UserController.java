package com.sonatype.infosec.owasp.a06;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a06.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a06.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a06.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a06.models.Option;
import com.sonatype.infosec.owasp.a06.models.User;
import com.sonatype.infosec.owasp.a06.repositories.UserRepository;

@RestController
public class UserController {

	@RequestMapping(value = "/user/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> findUser(String userId) throws PersistentDataException, NotFoundException {
		UserRepository userRepository = new UserRepository();

		Option<User> userOption = userRepository.getUser(userId);
		
		if (userOption.hasValue()) {
			return new ResponseEntity<User>(userOption.getValue(), HttpStatus.OK);
		}

		throw new NotFoundException(ApiErrorCode.USER_NOT_FOUND, "User not found");
	}
}
