package com.guli.edu.controller;

import com.guli.edu.entity.constant.WXConstant;
import com.guli.edu.exception.GuliException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@CrossOrigin
@Controller
@RequestMapping("api/ucenter/wx")
public class WXApiController {

    @GetMapping("login")
    public String getWxQRCode(){

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +
        "&scope=snsapi_login" +
        "&state=%s" +
        "#wechat_redirect";

        String redirectUrl = WXConstant.REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new GuliException(20001, "url编码出现错误");
        }

        String url = String.format(baseUrl,
                WXConstant.APP_ID,
                redirectUrl,
                "guli");
        return "redirect:"+url;
    }

    @GetMapping("callback")
    public String callback(String code,String state){

        System.out.println("code==="+code+"===state===="+state);
        return "redirect:local:3000";
    }
}
