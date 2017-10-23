package com.hand.security.core.validate.code;

import com.hand.security.core.properties.SecurityProperties;
import com.hand.security.core.validate.code.image.ImageCodeGenerator;
import com.hand.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.hand.security.core.validate.code.sms.SmsCodeGenerator;
import com.hand.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 20:13                                      * 
 ****************************************************************/
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    //条件，当不存在imageCodeGenerator Bean时调用下面的函数
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    //条件，当不存在imageCodeGenerator Bean时调用下面的函数
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeGenerator smsCodeSender(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }
}
