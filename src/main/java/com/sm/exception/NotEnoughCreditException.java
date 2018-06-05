package com.sm.exception;

public class NotEnoughCreditException extends RuntimeException {
	
	public NotEnoughCreditException(String string) {
		super(string);
	}
}
