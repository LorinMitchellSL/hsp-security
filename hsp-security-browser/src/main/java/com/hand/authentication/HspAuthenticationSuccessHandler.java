//package com.hand.authentication;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hand.security.core.properties.LoginResponseType;
//import com.hand.security.core.properties.SecurityProperties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///*******************Copyright Information************************
// *              AUTHOR: Lorin.Mitchell                           *
// *              DATE: 2017/10/18                                 *
// *              TIME: 20:16                                      *
// ****************************************************************/
//@Component("hspAuthenticationSuccessHandler")
//public class HspAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private SecurityProperties securityProperties;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws ServletException, IOException {
//        logger.info("Successful!");
//
//        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(authentication));
//        } else {
//            super.onAuthenticationSuccess(request, response, authentication);
//        }
//    }
//}
