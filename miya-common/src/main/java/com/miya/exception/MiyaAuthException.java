package com.miya.exception;

/**
 * @author Caixiaowei
 * @ClassName MiyaAuthException.java
 * @Description 通用异常类
 * @createTime 2020年05月12日 13:25:00
 */
public class MiyaAuthException extends Exception {

    private static final long serialVersionUID = -6916154462432027437L;

    public MiyaAuthException(String message){
        super(message);
    }
}
