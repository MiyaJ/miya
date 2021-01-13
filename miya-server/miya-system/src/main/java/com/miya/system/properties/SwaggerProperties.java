package com.miya.system.properties;

import lombok.Data;

/**
 * @author Caixiaowei
 * @ClassName SwaggerProperties.java
 * @Description TODO
 * @createTime 2020年05月14日 14:31:00
 */
@Data
public class SwaggerProperties {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;

    private String grantUrl;
    private String name;
    private String scope;
}
