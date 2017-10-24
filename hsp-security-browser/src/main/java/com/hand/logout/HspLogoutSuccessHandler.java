package com.hand.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.SimpleResponse;
import com.hand.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/24                                 *
 *              TIME: 11:31                                      * 
 ****************************************************************/
public class HspLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private SecurityProperties securityProperties;

    private ObjectMapper objectMapper = new ObjectMapper();

    public HspLogoutSuccessHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        logger.info("退出成功！");

        String url = securityProperties.getBrowser().getSignOutUrl();
        if (StringUtils.isBlank(url)) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功！")));
        }else {
            httpServletResponse.sendRedirect(url);
        }
    }
}
