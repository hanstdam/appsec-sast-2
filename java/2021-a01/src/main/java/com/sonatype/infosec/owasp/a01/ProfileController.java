package com.sonatype.infosec.owasp.a01;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sonatype.infosec.owasp.a01.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a01.models.Option;
import com.sonatype.infosec.owasp.a01.models.Profile;
import com.sonatype.infosec.owasp.a01.repositories.ProfileRepository;
import com.sonatype.infosec.owasp.a01.enumerations.ApiErrorCode;

@RestController
public class ProfileController {

	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Profile> getProfile(@RequestParam Integer id) throws NotFoundException {
		ProfileRepository profileRepository = new ProfileRepository();

		Option<Profile> profile = profileRepository.getProfile(id);
		if (profile.hasValue()) {
			return new ResponseEntity<Profile>(profile.getValue(), HttpStatus.OK);
		}
		
		throw new NotFoundException(ApiErrorCode.PROFILE_ENTITY_NOT_FOUND, "Profile entity not found");
	}
}
