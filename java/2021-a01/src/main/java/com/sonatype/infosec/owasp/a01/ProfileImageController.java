package com.sonatype.infosec.owasp.a01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonatype.infosec.owasp.a01.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a01.models.ProfileImage;
import com.sonatype.infosec.owasp.a01.enumerations.ApiErrorCode;

@RestController
public class ProfileImageController {

	@RequestMapping(value = "/profile-image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProfileImage> getProfileImage(@RequestParam String fileName) throws NotFoundException, IllegalStateException {
		Path rootPath = new File(this.getClass().getClassLoader().getResource("").getFile()).toPath();
		Path filePath = Paths.get(rootPath.toString(), "static", "img", fileName).normalize();
		File file = new File(filePath.toString());
		
		if (file.exists()) {
			try {
		        byte[] fileContent = Files.readAllBytes(file.toPath());
		        String data = Base64.getEncoder().encodeToString(fileContent);
				return new ResponseEntity<ProfileImage>(new ProfileImage(file.getName(), data), HttpStatus.OK);
		    } catch (IOException e) {
		        throw new IllegalStateException("Could not read profile image", e);
		    }
		}
		
		throw new NotFoundException(ApiErrorCode.PROFILE_IMAGE_NOT_FOUND, "Profile image not found");
	}
}
