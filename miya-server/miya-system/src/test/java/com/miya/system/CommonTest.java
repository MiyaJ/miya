package com.miya.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Caixiaowei
 * @ClassName CommonTest
 * @Description
 * @createTime 2021/4/8 14:35
 */
@Slf4j
public class CommonTest {

    @Test
    public void testP() {
        String user = "cai555777+";
        String pwd = new BCryptPasswordEncoder().encode(user);
        log.info("user: {}, pwd: {}", user, pwd);
    }
}
