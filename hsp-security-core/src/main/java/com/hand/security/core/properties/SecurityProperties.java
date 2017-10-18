package com.hand.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/18                                 *
 *              TIME: 17:00                                      *
 ****************************************************************/

@ConfigurationProperties(prefix = "hsp.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
