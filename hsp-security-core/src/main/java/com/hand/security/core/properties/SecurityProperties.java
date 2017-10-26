package com.hand.security.core.properties;

import com.hand.security.core.validate.code.ValidateCodeFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/18                                 *
 *              TIME: 17:00                                      *
 ****************************************************************/

@ConfigurationProperties(prefix = "hsp.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private OAuth2Properties oAuth2 = new OAuth2Properties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public OAuth2Properties getoAuth2() {
        return oAuth2;
    }

    public void setoAuth2(OAuth2Properties oAuth2) {
        this.oAuth2 = oAuth2;
    }
}
