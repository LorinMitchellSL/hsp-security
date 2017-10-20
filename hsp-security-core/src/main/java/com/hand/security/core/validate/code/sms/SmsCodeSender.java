package com.hand.security.core.validate.code.sms;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/20                                 *
 *              TIME: 14:06                                      * 
 ****************************************************************/
public interface SmsCodeSender {

    void send(String mobile,String code);
}
