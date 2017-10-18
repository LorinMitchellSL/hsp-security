package com.hand.security.core.properties;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/18                                 *
 *              TIME: 17:02                                      * 
 ****************************************************************/
public class BrowserProperties {
    private String loginPage = "/hsp-login-page.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
