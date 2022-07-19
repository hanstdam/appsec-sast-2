package com.sonatype.infosec.owasp.a10.models;

public class ServiceValidationResponse {
	String url;
	boolean isIqServer;
	
	public ServiceValidationResponse(String url, boolean isIqServer) {
		this.url = url;
		this.isIqServer = isIqServer;
	}
	
	public String getUrl() {
		return url;
	}
	
	public boolean getIsIqServer() {
		return isIqServer;
	}
}
