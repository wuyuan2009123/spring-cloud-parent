package com.springcloudstudy.productauth.secrity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by wuy on 2017/7/26.
 */
@Component
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if("user".equals(s)){
            return new SecurityUser("user","pwd","user-role");
        }else if ("admin".equals(s)){
            return new SecurityUser("admin","pwd","admin-role");
        }
        return null;
    }
}
