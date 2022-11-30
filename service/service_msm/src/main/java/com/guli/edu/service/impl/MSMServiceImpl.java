package com.guli.edu.service.impl;

import com.guli.edu.constant.ConstantTencentSMS;
import com.guli.edu.service.MSMService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MSMServiceImpl implements MSMService {

    public  Boolean sendSMS(String phoneNumber, String verificationCode, Integer timeOut) {

        if (StringUtils.isEmpty(phoneNumber)) return false;
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
        // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
        Credential cred = new Credential(ConstantTencentSMS.SECRET_ID, ConstantTencentSMS.SECRET_KEY);
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        SmsClient client = new SmsClient(cred, "ap-nanjing", clientProfile);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = {"+86" + phoneNumber};
        req.setPhoneNumberSet(phoneNumberSet1);
        req.setSmsSdkAppid(ConstantTencentSMS.SMS_SDK_APP_ID);
        req.setTemplateID(ConstantTencentSMS.TEMPLATE_ID);
        req.setSign(ConstantTencentSMS.SIGN_NAME);
        String[] templateParamSet1 = {verificationCode, timeOut+""};
        req.setTemplateParamSet(templateParamSet1);
        // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
        SendSmsResponse resp = null;
        try {
            resp = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return false;
        }
        // 输出json格式的字符串回包
        System.out.println(SendSmsResponse.toJsonString(resp));
        return true;
    }


}

