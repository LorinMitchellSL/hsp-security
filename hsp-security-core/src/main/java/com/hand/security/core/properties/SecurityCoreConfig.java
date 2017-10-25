package com.hand.security.core.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/18                                 *
 *              TIME: 17:07                                      * 
 ****************************************************************/

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        //实现类可以根据具体情况进行编写
        return new BCryptPasswordEncoder();
    }

}
