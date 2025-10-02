package com.api.constants;

public enum Platform {

	FRONT_DESK(2);
	private int code;
	private Platform(int code) {
		 this.code=code;
	}
	public int getCode() {
		return code;
	}
}
