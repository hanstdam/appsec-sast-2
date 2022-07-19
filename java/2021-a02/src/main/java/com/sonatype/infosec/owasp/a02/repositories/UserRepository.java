package com.sonatype.infosec.owasp.a02.repositories;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.sonatype.infosec.owasp.a02.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a02.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a02.models.User;

public class UserRepository {
	static final String url = "jdbc:mysql://localhost:8081/sql";  
	static final String userName = "xyz";  
	static final String password = "abc"; //CWE-259: Hard-coded password
	
	public Integer createUser(User user) throws PersistentDataException {
		final String sql = "insert into users (username, hashed_password, age) values (?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(url, userName, password)) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getHashedPassword());
				statement.setInt(3, user.getAge());
				
				return statement.executeUpdate();
			} catch (SQLException e) {
				throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_CREATE_USER, "Cannot create user");
			} catch (NoSuchAlgorithmException e) {
				throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_FIND_HASH_ALGORITHM, "Cannot find hash algorithm used to hash user password");
			}
		} catch (SQLException e) {
			throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_CONNECT, "Cannot connect to database");
		}
	}
}
