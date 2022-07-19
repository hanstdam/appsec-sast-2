package com.sonatype.infosec.owasp.a01.models;

public class Profile {
	String name;
	Integer age;
	String socialSecurityNumber;
	
	public Profile(String name, Integer age, String ssn) {
		this.name = name;
		this.age = age;
		this.socialSecurityNumber = ssn;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getAge() {
		return this.age;
	}
	
	public String getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}
}
