package com.guli.edu.controller;

import com.guli.edu.commonUtils.JwtUtil;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.UCenterMember;
import com.guli.edu.entity.vo.RegisterVo;
import com.guli.edu.service.UCenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("educenter/member")
public class UCenterMemberController {

    @Autowired
    private UCenterMemberService uCenterMemberService;

    @PostMapping("login")
    public RMap login(@RequestBody UCenterMember uCenterMember){

        String token = uCenterMemberService.login(uCenterMember);
        return RMap.ok().data("token",token);
    }

    @PostMapping("register")
    public RMap register(@RequestBody RegisterVo registerVo){

        uCenterMemberService.register(registerVo);
        return RMap.ok();
    }

    @GetMapping("getMemberInfo")
    public RMap getMemberInfo(HttpServletRequest request) {

        String memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
        UCenterMember member = uCenterMemberService.getById(memberIdByJwtToken);
        return RMap.ok().data("userInfo",member);
    }
}
