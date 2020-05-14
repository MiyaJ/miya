package com.miya.entity;

import com.miya.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Caixiaowei
 * @ClassName MiyaResponse.java
 * @Description TODO
 * @createTime 2020年05月12日 13:24:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiyaResponse<T> implements Serializable {

    private static final long serialVersionUID = -8713837118340960775L;

    private int code;

    private String message;

    private T data;

    public MiyaResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public MiyaResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static MiyaResponse success() {
        return new MiyaResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }

    public static MiyaResponse success(String message, Object data) {
        return new MiyaResponse(ResponseEnum.SUCCESS.getCode(), message, data);
    }

    public static MiyaResponse success(String message) {
        return new MiyaResponse(ResponseEnum.SUCCESS.getCode(), message);
    }

    public static MiyaResponse success(Object data) {
        return new MiyaResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data);
    }

    public static MiyaResponse error(String message) {
        return new MiyaResponse(ResponseEnum.ERROR.getCode(), message);
    }

    public static MiyaResponse error(String message, Object data) {
        return new MiyaResponse(ResponseEnum.ERROR.getCode(), message, data);
    }

    public static MiyaResponse error() {
        return new MiyaResponse(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getDesc());
    }

}
