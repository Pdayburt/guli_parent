package com.guli.edu.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantTencentSMS implements InitializingBean {

    @Value("${tencent.SMS.secretId}")
    private String secretId;
    @Value("${tencent.SMS.secretKey}")
    private String secretKey;
    @Value("${tencent.SMS.smsSdkAppId}")
    private String smsSdkAppId;
    @Value("${tencent.SMS.signName}")
    private String signName;
    @Value("${tencent.SMS.templateId}")
    private String templateId;

    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String SMS_SDK_APP_ID;
    public static String SIGN_NAME;
    public static String TEMPLATE_ID;

    @Override
    public void afterPropertiesSet() throws Exception {

        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        SMS_SDK_APP_ID = smsSdkAppId;
        SIGN_NAME = signName;
        TEMPLATE_ID = templateId;
    }
}
