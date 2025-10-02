package com.api.constants;

public enum Product {
	
	NEXUS(1), PIXEL(2);
	
	int code;
	private Product(int code) {
		this.code=code;
	}
	public int getCode() {
		return code;
	}
	
	
	
}
