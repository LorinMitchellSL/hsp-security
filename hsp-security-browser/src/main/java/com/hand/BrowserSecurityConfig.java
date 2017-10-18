package com.hand;

import com.hand.authentication.HspAuthentiationFailureHandler;
import com.hand.authentication.HspAuthenticationSuccessHandler;
import com.hand.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private HspAuthenticationSuccessHandler hspAuthenticationSuccessHandler;

    @Autowired
    private HspAuthentiationFailureHandler hspAuthentiationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        //实现类可以根据具体情况进行编写
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

//        http.httpBasic() 弹出对话框的SpringSecurity安全认证
        http.formLogin()
                .loginPage("/hsp-login-page.html")  //指定登陆界面的地址
                .loginProcessingUrl("/authentication/form")
                .successHandler(hspAuthenticationSuccessHandler)
                .failureHandler(hspAuthentiationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/hsp-login-page.html",
                        securityProperties.getBrowser().getLoginPage()).permitAll()    //给予登陆界面授权
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
