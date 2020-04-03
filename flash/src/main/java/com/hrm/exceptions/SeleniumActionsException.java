package com.hrm.exceptions;

public class SeleniumActionsException extends Exception {
    public SeleniumActionsException(String msg) {
        super(msg);
    }

    public SeleniumActionsException(String msg, Exception e) {
        super(msg, e);
    }

}
