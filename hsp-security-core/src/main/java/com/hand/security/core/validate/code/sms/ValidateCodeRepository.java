package com.hand.security.core.validate.code.sms;

import com.hand.security.core.validate.code.ValidateCode;
import com.hand.security.core.validate.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/25                                 *
 *              TIME: 19:56                                      * 
 ****************************************************************/
public interface ValidateCodeRepository {
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest request,ValidateCodeType validateCodeType);

    void remove(ServletWebRequest request,ValidateCodeType validateCodeType);
}
