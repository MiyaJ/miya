package com.miya.auth.service;

import com.miya.auth.mapper.MenuMapper;
import com.miya.entity.MiyaAuthUser;
import com.miya.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Caixiaowei
 * @ClassName MiyaUserDetailService.java
 * @Description TODO
 * @createTime 2020年05月12日 13:20:00
 */
@Service
public class MiyaUserDetailService implements UserDetailsService {

    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findByName(username);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus())) {
                notLocked = true;
            }
            MiyaAuthUser authUser = new MiyaAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true,
                    notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }

}
