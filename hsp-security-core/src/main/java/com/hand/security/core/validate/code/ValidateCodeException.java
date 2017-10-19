package com.hand.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 13:56                                      * 
 ****************************************************************/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
