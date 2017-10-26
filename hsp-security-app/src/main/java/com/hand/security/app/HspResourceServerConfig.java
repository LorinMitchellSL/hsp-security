package com.hand.security.app;

import com.hand.security.app.authentication.HspAuthenticationFailureHandler;
import com.hand.security.app.authentication.HspAuthenticationSuccessHandler;
import com.hand.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.hand.security.core.properties.SecurityConstants;
import com.hand.security.core.properties.SecurityProperties;
import com.hand.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.sql.DataSource;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/25                                 *
 *              TIME: 11:21                                      * 
 ****************************************************************/
@Configuration
@EnableResourceServer
public class HspResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private HspAuthenticationSuccessHandler hspAuthenticationSuccessHandler;

    @Autowired
    private HspAuthenticationFailureHandler hspAuthenticationFailureHandler;

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository(){
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        //启动时创建表
////        tokenRepository.setCreateTableOnStartup(true);
//        return  tokenRepository;
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.formLogin()
//                .loginPage("/hsp-login-page.html")  //指定登陆界面的地址
                .loginProcessingUrl("/authentication/form")
                .successHandler(hspAuthenticationSuccessHandler)
                .failureHandler(hspAuthenticationFailureHandler);
        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        SecurityConstants.DEFAULT_SESSION_INVALID_URL)
                .permitAll()    //给予登陆界面授权
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
