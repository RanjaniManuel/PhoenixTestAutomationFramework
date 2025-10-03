package com.api.constants;

public enum Model {
	NEXUS_2_BLUE(1), GALLEXY(1);

	private int code;

	private Model(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
