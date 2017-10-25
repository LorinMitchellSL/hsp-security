package com.hand.security.core.support;

/*******************Copyright Information************************
 *              USER: Lorin.Mitchell                             *
 *              DATE: 2017/10/18                                    *
 *              TIME: 16:45                                    * 
 ****************************************************************/
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
