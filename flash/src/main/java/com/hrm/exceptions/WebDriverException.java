package com.hrm.exceptions;

public class WebDriverException extends Exception {
	public JiveWebDriverException(String msg) {
	        super(msg);
	    }

	public JiveWebDriverException(String msg, Exception e) {
	        super(msg, e);
	    }
}
