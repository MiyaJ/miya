package com.miya.exception;

/**
 * @author Caixiaowei
 * @ClassName PayException
 * @Description
 * @createTime 2021/2/8 10:52
 */
public class PayException extends RuntimeException{

    public PayException() {
    }

    public PayException(String message) {
        super(message);
    }

    public PayException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayException(Throwable cause) {
        super(cause);
    }

    public PayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
