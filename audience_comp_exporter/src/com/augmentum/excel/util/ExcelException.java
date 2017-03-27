package com.augmentum.excel.util;

public class ExcelException extends Exception {
    private static final long serialVersionUID = 3590896907801819434L;

    public ExcelException(String message) {
		super(message);
	}
	
	public ExcelException(Throwable cause) {
		super(cause);
	}
}
