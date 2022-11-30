package com.guli.oss.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    public static  String END_POINT = null;
    public static  String KEY_ID=null;
    public static  String KEY_SECRET=null;
    public static  String BUCK_NAME=null;


    @Override
    public void afterPropertiesSet() throws Exception {

        END_POINT=endPoint;
        KEY_ID=keyId;
        KEY_SECRET=keySecret;
        BUCK_NAME=bucketName;


    }
}
