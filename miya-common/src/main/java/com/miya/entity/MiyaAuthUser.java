package com.miya.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName MiyaAuthUser.java
 * @Description TODO
 * @createTime 2020年05月12日 13:21:00
 */
@Data
public class MiyaAuthUser implements Serializable {

    private static final long serialVersionUID = -1748289340320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
