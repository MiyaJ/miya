package com.miya.enums;

/**
 * @author Caixiaowei
 * @ClassName MiyaResponseEnum.java
 * @Description 响应枚举
 * @createTime 2020年05月14日 10:37:00
 */
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS(0,"SUCCESS"),

    /**
     * 失败
     */
    ERROR(1,"ERROR");


    private int code;

    private String desc;

    ResponseEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
