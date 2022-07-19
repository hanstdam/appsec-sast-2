package com.sonatype.infosec.owasp.a09.repositories;

import java.util.ArrayList;

import com.sonatype.infosec.owasp.a09.models.Option;
import com.sonatype.infosec.owasp.a09.models.User;

public class UserRepository {
	ArrayList<User> data;
	
	public UserRepository() {
		data = new ArrayList<User>();
		data.add(new User(1, "Hans", 5));
		data.add(new User(2, "Long", 6));
		data.add(new User(10, "AdminMike", 7));
	}
	
	public Option<User> getUser(Integer id) {
		
		for (int i = 0; i < data.size(); i++) {
			User user = data.get(i);
			if (user.getUserId().equals(id)) {
				return new Option<User>(user);
			}
		}
		
		return new Option<User>();
	}
}
