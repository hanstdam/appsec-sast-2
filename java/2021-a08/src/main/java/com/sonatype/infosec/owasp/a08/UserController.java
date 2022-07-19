package com.sonatype.infosec.owasp.a08;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a08.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a08.exceptions.BadRequestException;
import com.sonatype.infosec.owasp.a08.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a08.models.User;
import com.sonatype.infosec.owasp.a08.repositories.UserRepository;

@RestController
public class UserController {
	
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> createUser(HttpServletRequest request) throws PersistentDataException, BadRequestException {
		UserRepository userRepository = new UserRepository();

		User user = null;
		try {
			InputStream input = request.getInputStream();
			
			if (input != null) {
				ObjectInputStream in = new ObjectInputStream(input);
				// CWE-502: Deserialization of Untrusted Data
				user = (User) in.readObject();
				in.close();
			}

			input.close();
			
		} catch (Exception e) {
			throw new BadRequestException(ApiErrorCode.USER_CONTROLLER_CANNOT_DESERIALIZE_USER, "Unable to read supplied user data");
		}

		User createdUser = userRepository.createUser(user);
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}
}
