package com.hand.code;

import com.hand.security.core.validate.code.image.ImageCode;
import com.hand.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 20:22                                      * 
 ****************************************************************/
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("此处编写验证生成逻辑");
        return null;
    }
}
