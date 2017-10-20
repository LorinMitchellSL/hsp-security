package com.hand.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/20                                 *
 *              TIME: 14:42                                      * 
 ****************************************************************/
public interface ValidateCodeProcessor {
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    void create(ServletWebRequest request) throws Exception;

    void validate(ServletWebRequest servletWebRequest);
}
