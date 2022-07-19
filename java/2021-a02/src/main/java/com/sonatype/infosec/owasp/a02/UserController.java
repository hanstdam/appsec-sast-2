package com.sonatype.infosec.owasp.a02;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a02.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a02.models.BasicUser;
import com.sonatype.infosec.owasp.a02.models.User;
import com.sonatype.infosec.owasp.a02.repositories.UserRepository;

@RestController
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<BasicUser> saveUser(@RequestBody User user) throws PersistentDataException {
		UserRepository userRepository = new UserRepository();

		user.ensurePasswordIsPopulated();
		userRepository.createUser(user);
		
		return new ResponseEntity<BasicUser>(user.toBasic(), HttpStatus.CREATED);
	}
}
