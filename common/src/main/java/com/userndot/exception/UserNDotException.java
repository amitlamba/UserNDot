package com.userndot.exception;

public class UserNDotException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5177768882169433109L;
	private Object exceptionData;

	public UserNDotException (String message) {
		super(message);
	}
	
	public UserNDotException (Throwable cause) {
		super (cause);
	}
	
	public UserNDotException (String message, Throwable cause) {
		super (message, cause);
	}

    public UserNDotException (String message, Throwable cause, Object data) {
        super (message, cause);
        this.exceptionData = data;
    }

    public Object getExceptionData() {
        return exceptionData;
    }

    public void setExceptionData(Object exceptionData) {
        this.exceptionData = exceptionData;
    }

}
