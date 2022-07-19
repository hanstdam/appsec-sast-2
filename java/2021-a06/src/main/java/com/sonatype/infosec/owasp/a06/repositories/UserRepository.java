package com.sonatype.infosec.owasp.a06.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;

import com.sonatype.infosec.owasp.a06.enumerations.ApiErrorCode;
import com.sonatype.infosec.owasp.a06.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a06.models.Option;
import com.sonatype.infosec.owasp.a06.models.User;

public class UserRepository {
	
	@Autowired
	private DataSource dataSource;
	
	public Option<User> getUser(String userId) throws PersistentDataException {
		final String sql = "select user_id, name, age from users where user_id = ?";
		try (Connection connection = dataSource.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, userId);
				try (ResultSet rs = statement.executeQuery()) {
					
					if (rs.next()) {
						return new Option<User>(new User(rs.getString("user_id"), rs.getString("name"), rs.getInt("age")));
					}
					return new Option<User>();

				} catch (SQLException e) {
					throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_EXECUTE_USER_SEARCH, "Cannot execute user search");
				}
			} catch (SQLException e) {
				throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_CREATE_USER_SEARCH_STATEMENT, "Cannot create user search statement");
			}
		} catch (SQLException e) {
			throw new PersistentDataException(ApiErrorCode.PERSISTANT_DATA_CANNOT_CONNECT, "Cannot connect to database");
		}
	}
}
