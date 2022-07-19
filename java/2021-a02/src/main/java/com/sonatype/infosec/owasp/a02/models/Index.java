package com.sonatype.infosec.owasp.a02.models;

public class Index {
	String framework;
	Integer version;
	String id;
	String title;
	
	public Index(String framework, Integer version, String id, String title) {
		this.framework = framework;
		this.version = version;
		this.id = id;
		this.title = title;
	}
	
	public String getFramework() {
		return framework;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
}
