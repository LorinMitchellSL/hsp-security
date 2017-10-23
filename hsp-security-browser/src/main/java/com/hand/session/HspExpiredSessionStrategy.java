package com.hand.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/23                                 *
 *              TIME: 20:07                                      * 
 ****************************************************************/
public class HspExpiredSessionStrategy implements SessionInformationExpiredStrategy{

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset-UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write("并发登陆！");
    }
}
