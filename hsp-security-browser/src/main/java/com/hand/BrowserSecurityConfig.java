package com.hand;

import com.hand.authentication.HspAuthentiationFailureHandler;
import com.hand.authentication.HspAuthenticationSuccessHandler;
import com.hand.security.core.properties.SecurityProperties;
import com.hand.security.core.validate.code.ValidateCodeController;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.hand.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private HspAuthenticationSuccessHandler hspAuthenticationSuccessHandler;

    @Autowired
    private HspAuthentiationFailureHandler hspAuthentiationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        //实现类可以根据具体情况进行编写
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //启动时创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return  tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();

        validateCodeFilter.setAuthenticationFailureHandler(hspAuthentiationFailureHandler);

        validateCodeFilter.setSecurityProperties(securityProperties);

        validateCodeFilter.afterPropertiesSet();

//        http.httpBasic() 弹出对话框的SpringSecurity安全认证
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/hsp-login-page.html")  //指定登陆界面的地址
                .loginProcessingUrl("/authentication/form")
                .successHandler(hspAuthenticationSuccessHandler)
                .failureHandler(hspAuthentiationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/hsp-login-page.html",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()    //给予登陆界面授权
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
