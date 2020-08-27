package kr.co.cm29.homework.exception;

public class SoldOutException extends RuntimeException {
    public SoldOutException(String msg, Throwable t) {
        super(msg, t);
    }

    public SoldOutException(String msg) {
        super(msg);
    }

    public SoldOutException() {
        super();
    }
}
