package com.sm.exception;

public class AccountNotFoundException extends RuntimeException{

	public AccountNotFoundException(String string) {
		super(string);
	}

}
