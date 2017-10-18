package com.hand;

import com.hand.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*******************Copyright Information************************
 *              USER: Lorin.Mitchell                             *
 *              DATE: 2017/10/18                                 *
 *              TIME: 16:29                                      *
 ****************************************************************/

@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request,
                                        HttpServletResponse response){

        SavedRequest savedRequest = requestCache.getRequest(request,response);

        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是："+targetUrl);
            if (StringUtils.endsWith(targetUrl,".html")){
                try {
                    redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回json
        return new SimpleResponse("需要认证！");
    }
}
