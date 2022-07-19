package com.sonatype.infosec.owasp.a02.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sonatype.infosec.owasp.a02.utilities.PasswordGenerator;

public class User extends BasicUser {
	String plainTextPassword;
	
	public String getHashedPassword() throws NoSuchAlgorithmException {
		// CWE-327: Use of a Broken or Risky Cryptographic Algorithm
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(plainTextPassword.getBytes());
	    byte[] digest = md.digest();
	    return String.format("%032x", new BigInteger(1, digest)).toUpperCase();
	}
	
	public void setPassword(String plainTextPassword) {
		this.plainTextPassword = plainTextPassword;
	}
	
	public BasicUser toBasic() {
		BasicUser basic = new BasicUser();
		basic.setUsername(username);
		basic.setAge(age);
		return basic;
	}
	
	public void ensurePasswordIsPopulated() {
		if (plainTextPassword == null || plainTextPassword.trim().isEmpty()) {
			PasswordGenerator generator = new PasswordGenerator();
			plainTextPassword = generator.get();
		}
	}
}
