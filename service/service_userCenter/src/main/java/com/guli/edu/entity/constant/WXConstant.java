package com.guli.edu.entity.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WXConstant implements InitializingBean {


    @Value("${wx.open.appid}")
    private String appId;
    @Value("${wx.open.appsecret}")
    private String appSecret;
    @Value("${wx.open.redirecturl}")
    private String redirectUrl;

    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_SECRET = appSecret;
        REDIRECT_URL = redirectUrl;
    }
}
