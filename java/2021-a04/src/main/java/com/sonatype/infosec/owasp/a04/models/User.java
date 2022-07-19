package com.sonatype.infosec.owasp.a04.models;

public class User {
	Integer userId;
	String name;
	Integer age;
	
	public User(Integer userId, String name, Integer age) {
		this.userId = userId;
		this.name = name;
		this.age = age;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getAge() {
		return age;
	}
}
