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
public class HspExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy{

    public HspExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        onSessionInvalid(sessionInformationExpiredEvent.getRequest(),sessionInformationExpiredEvent.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
