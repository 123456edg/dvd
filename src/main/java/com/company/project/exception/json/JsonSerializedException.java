package com.company.project.exception.json;



/**
 * 自定义JSON异常
 */
public class JsonSerializedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JsonSerializedException(String message) {
		super(message);
	}

	public JsonSerializedException(Throwable cause) {
		super(cause);
	}

	public JsonSerializedException(String message, Throwable cause) {
		super(message, cause);
	}

}
