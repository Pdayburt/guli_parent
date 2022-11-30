package com.guli.edu.controller;


import com.guli.edu.commonUtils.RMap;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public RMap userLogin() {
        return RMap.ok().data("token", "admin");
    }

    @GetMapping("info")
    public RMap getInfo(){
        return RMap.ok().data("roles","admin").data("name", "admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
