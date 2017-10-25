package com.hand.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HspUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //此处需要查询数据库
        logger.info("登陆用户名："+s);
        //本应将该转换过后的字符串写到数据库中
        //在登陆时，使用passwordEncoder.matches()方法比对密码
        String psw = passwordEncoder.encode("123456");
        logger.info(psw);
        return new User(s,psw, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
//        return new User("123456",
//                "123456",
//                true,
//                true,
//                true,
//                false,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
