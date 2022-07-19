package com.sonatype.infosec.owasp.a03;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a03.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a03.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a03.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a03.models.Option;
import com.sonatype.infosec.owasp.a03.models.User;
import com.sonatype.infosec.owasp.a03.models.UserSetting;
import com.sonatype.infosec.owasp.a03.repositories.UserRepository;
import com.sonatype.infosec.owasp.a03.repositories.UserSettingRepository;

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

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String getUserPage(String userName) {
		// CWE-79: Cross-site Scripting
		return "<html>\n"
			+ "<header><title>Welcome</title></header>\n"
			+ "<body>\n"
			+ "Hello " + userName + "\n"
			+ "</body>\n"
			+ "</html>";
	}

	@RequestMapping(value = "/user/settings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserSetting> getUserSettings(String userId) throws NotFoundException {
		UserSettingRepository userSettingRepository = new UserSettingRepository();

		Option<UserSetting> userSettingOption = userSettingRepository.getUserSettings(userId);
		
		if (userSettingOption.hasValue()) {
			return new ResponseEntity<UserSetting>(userSettingOption.getValue(), HttpStatus.OK);
		}

		throw new NotFoundException(ApiErrorCode.USER_SETTING_NOT_FOUND, "User settings not found");
	}
}
