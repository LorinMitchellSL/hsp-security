package com.hand.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 20:00                                      * 
 ****************************************************************/
public interface ValidateCodeGenerator {

    ImageCode createImageCode(ServletWebRequest request);
}
