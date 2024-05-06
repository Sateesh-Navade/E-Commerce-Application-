package org.jsp.e_commerce.exception;

public class AddressNotFoundException extends RuntimeException{
	public AddressNotFoundException(String message) {
		super(message);
	}
}
