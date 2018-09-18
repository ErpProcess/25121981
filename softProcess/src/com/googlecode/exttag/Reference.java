package com.googlecode.exttag;

public final class Reference {

	private final String value;

	public Reference(String value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
