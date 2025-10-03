package com.api.constants;

public enum ServiceCenter {
	SERVICE_CENTER_A(1);
	private int code;
	private ServiceCenter(int code) {
		 this.code=code;
	}
	public int getCode() {
		return code;
	}
}
