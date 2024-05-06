package org.jsp.e_commerce.exception;


public class InvalidCredentialsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3957359386357626480L;

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
