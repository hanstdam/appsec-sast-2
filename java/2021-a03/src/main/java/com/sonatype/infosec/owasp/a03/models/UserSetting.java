package com.sonatype.infosec.owasp.a03.models;

public class UserSetting {
	String userId;
	String role;
	boolean isMultiFactorAuthenticationEnabled;
	String managerId;
	
	public UserSetting(String userId, String role, boolean isMFAenabled, String managerId) {
		this.userId = userId;
		this.role = role;
		this.isMultiFactorAuthenticationEnabled = isMFAenabled;
		this.managerId = managerId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getrole() {
		return role;
	}
	
	public boolean isMultiFactorAuthenticationEnabled() {
		return isMultiFactorAuthenticationEnabled;
	}
	
	public String getManagerId() {
		return managerId;
	}
}
