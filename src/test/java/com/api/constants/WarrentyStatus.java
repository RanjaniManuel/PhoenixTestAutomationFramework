package com.api.constants;

public enum WarrentyStatus {
		IN_WARRENTY(1),
		OUT_OF_WARRENTY(2); 
		private int code;
		private WarrentyStatus(int code) {
			 this.code=code;
		}
		public int getCode() {
			return code;
		}
	 
}
