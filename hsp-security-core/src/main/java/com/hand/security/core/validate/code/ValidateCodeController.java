package com.hand.security.core.validate.code;

import com.hand.security.core.properties.SecurityConstants;
import com.hand.security.core.validate.code.sms.SmsCodeSender;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 10:48                                      * 
 ****************************************************************/
@RestController
public class ValidateCodeController {


    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;
//    private ValidateCodeProcessorHolder validateCodeProcessorHolder;


    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        System.out.println(type);
        validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request,response));
    }
}
