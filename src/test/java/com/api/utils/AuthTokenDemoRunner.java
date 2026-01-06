package com.api.utils;

import com.api.constants.Role;

public class AuthTokenDemoRunner {
	public static void main(String[] args) throws InterruptedException {
		for(int i=0; i<100; i++) {
			String token = AuthTokenProvider.getToken(Role.FD);	
			Thread.sleep(3000);
			System.out.println(token);
		}
	}

}
