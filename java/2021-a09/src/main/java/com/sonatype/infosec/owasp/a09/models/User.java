package com.sonatype.infosec.owasp.a09.models;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -6153558773764167884L;
	
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
