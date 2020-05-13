package com.miya.exception;

/**
 * @author Caixiaowei
 * @ClassName ValidateCodeException.java
 * @Description 验证码异常
 * @createTime 2020年05月13日 15:58:00
 */
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message){
        super(message);
    }
}
