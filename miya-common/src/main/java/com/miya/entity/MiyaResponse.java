package com.miya.entity;

import lombok.Data;

import java.util.HashMap;

/**
 * @author Caixiaowei
 * @ClassName MiyaResponse.java
 * @Description TODO
 * @createTime 2020年05月12日 13:24:00
 */
@Data
public class MiyaResponse extends HashMap<String, Object>  {

    private static final long serialVersionUID = -8713837118340960775L;

    public MiyaResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public MiyaResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public MiyaResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
