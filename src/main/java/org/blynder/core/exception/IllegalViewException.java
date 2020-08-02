package org.blynder.core.exception;

/**
 * 
 * Exception class used when the framework tries to create a view with an invalid
 * parameter.
 *
 */
public class IllegalViewException extends Exception{

	private static final long serialVersionUID = 1L;

	public IllegalViewException(String msg) {
		super(msg);
	}
	
}
