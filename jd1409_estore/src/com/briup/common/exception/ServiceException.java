package com.briup.common.exception;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = -838066844714107845L;
	
	private String msg;
	
	public ServiceException() {
	}

	public ServiceException(String msg,Exception e) {
		super(msg,e);
		this.msg = msg;
	}
	
	public ServiceException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
