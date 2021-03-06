package com.hand.security.core.validate.code.image;

import com.hand.security.core.properties.SecurityProperties;
import com.hand.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 20:07                                      * 
 ****************************************************************/
public class ImageCodeGenerator implements ValidateCodeGenerator {

    //引入配置信息
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        //从请求中获取验证码的宽和高度信息，如果请求中有传入则使用传入，没有的话使用下一层的配置
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",securityProperties.getCode().getImage().getWidth());
        int height =ServletRequestUtils.getIntParameter(request.getRequest(),"height",securityProperties.getCode().getImage().getHeight());
        BufferedImage image= new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
        g.setColor(getRandColor(160,200));
        for (int i = 0; i < 155; i++ ){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x + xl,y + yl);
        }

        String sRand = "";

        //获取配置中验证码的位数，不再是写死的位数
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand,13 * i + 6, 16);
        }

        g.dispose();

        //从配置中加过期时间
        return new ImageCode(image,sRand,securityProperties.getCode().getImage().getExpireIn());

    }


    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255){
            fc = 255;
        }

        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return  new Color(r,g,b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
