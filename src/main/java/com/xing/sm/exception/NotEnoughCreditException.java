package com.xing.sm.exception;

public class NotEnoughCreditException extends RuntimeException {
	
	public NotEnoughCreditException(String string) {
		super(string);
	}
}
