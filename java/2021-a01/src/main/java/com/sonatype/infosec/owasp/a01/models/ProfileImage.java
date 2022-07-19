package com.sonatype.infosec.owasp.a01.models;

public class ProfileImage {
	String name;
	String data;
	
	public ProfileImage(String name, String data) {
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public String getData() {
		return data;
	}
}
