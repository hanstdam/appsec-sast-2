package com.sonatype.infosec.owasp.a08.models;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -6153558773764167884L;
	
	String userId;
	String name;
	Integer age;
	
	public User(String userId, String name, Integer age) {
		this.userId = userId;
		this.name = name;
		this.age = age;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getAge() {
		return age;
	}
}
