package com.vivo.uhost.comm.exception;

public class CacheNotFoundException extends RuntimeException {

    public CacheNotFoundException() {
    }

    public CacheNotFoundException(String msg) {
        super(msg);
    }

    public CacheNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CacheNotFoundException(Throwable t) {
        super(t);
    }
}
