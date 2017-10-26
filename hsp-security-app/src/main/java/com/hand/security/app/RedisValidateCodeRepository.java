package com.hand.security.app;

import com.hand.security.core.validate.code.ValidateCode;
import com.hand.security.core.validate.code.ValidateCodeException;
import com.hand.security.core.validate.code.ValidateCodeType;
import com.hand.security.core.validate.code.sms.ValidateCodeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/25                                 *
 *              TIME: 20:02                                      * 
 ****************************************************************/
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository{

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request,validateCodeType),code,30, TimeUnit.MINUTES);
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("deviceId为空");
        }
        return "code"+validateCodeType.toString().toLowerCase()+":"+deviceId;
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        Object value = redisTemplate.opsForValue().get(buildKey(request,validateCodeType));
        if (value == null){
            return null;
        }
        return (ValidateCode)value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request,validateCodeType));
    }
}
