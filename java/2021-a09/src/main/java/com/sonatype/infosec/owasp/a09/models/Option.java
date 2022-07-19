package com.sonatype.infosec.owasp.a09.models;

public class Option<T> {
	T value;
	boolean hasValue;
	
	public Option() {
		hasValue = false;
	}
	
	public Option(T value) {
		this.value = value;
		hasValue = true;
	}
	
	public boolean hasValue() {
		return hasValue;
	}
	
	public T getValue() {
		return value;
	}
}

