package com.sonatype.infosec.owasp.a01.repositories;

import java.util.HashMap;
import com.sonatype.infosec.owasp.a01.models.Option;
import com.sonatype.infosec.owasp.a01.models.Profile;

public class ProfileRepository {
	HashMap<Integer, Profile> data;
	
	public ProfileRepository() {
		data = new HashMap<>();
		data.put(1, new Profile("Hans", 5, "111-222-333"));
		data.put(2, new Profile("Long", 6, "444-555-666"));
		data.put(10, new Profile("AdminMike", 7, "777-888-999"));
	}
	
	public Option<Profile> getProfile(Integer id) {
		if (data.containsKey(id)) {
			return new Option<Profile>(data.get(id));
		}
		
		return new Option<Profile>();
	}
}
