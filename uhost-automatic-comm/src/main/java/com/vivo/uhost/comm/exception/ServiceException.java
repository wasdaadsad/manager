package com.vivo.uhost.comm.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable t) {
        super(msg, t);
    }

    public ServiceException(Throwable t) {
        super(t);
    }
}
