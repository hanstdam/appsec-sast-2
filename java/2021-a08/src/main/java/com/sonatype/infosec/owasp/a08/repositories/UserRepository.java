package com.sonatype.infosec.owasp.a08.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.sonatype.infosec.owasp.a08.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a08.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a08.models.User;

public class UserRepository {
	final String relativeUsersFilePath = "data/users.bin";
	
	private String getUsersFilePath() {
		Path rootPath = new File(this.getClass().getClassLoader().getResource("").getFile()).toPath();
		Path usersFilePath = Paths.get(rootPath.toString(), relativeUsersFilePath).normalize();
		return usersFilePath.toString();
	}
	
	public User createUser(User user) throws PersistentDataException {
		ArrayList<User> users = new ArrayList<User>();
		String usersFilePath = getUsersFilePath();
		try {
			FileInputStream file = new FileInputStream(usersFilePath);
			ObjectInputStream in = new ObjectInputStream(file);
			// CWE-502: Deserialization of Untrusted Data
			users = (ArrayList<User>) in.readObject(); // Is the static file untrusted?
			in.close();
		} catch (Exception e) {
			throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_DESERIALIZE_USER_LIST, "Cannot deserialize persistent user list");
		}
		
		// TODO: Check if user id is duplicate
		users.add(user);
		
		try {
			FileOutputStream file = new FileOutputStream(usersFilePath);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.reset();
			out.writeObject(users);
			out.close();
		} catch (Exception e) {
			throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_SERIALIZE_USER_LIST, "Cannot serialize persistent user list");
		}
		
		return user;
	}
}
