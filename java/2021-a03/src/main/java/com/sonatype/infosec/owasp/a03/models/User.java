package com.sonatype.infosec.owasp.a03.models;

public class User {
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
