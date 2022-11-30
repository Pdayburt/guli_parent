package com.guli.edu.controller;

import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.CrmBanner;
import com.guli.edu.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("educms/bannerFront")
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("查询所有的banner")
    @GetMapping("getBanner")
    public RMap getBanner(){

        List<CrmBanner> list = crmBannerService.selectBanner();
        return RMap.ok().data("item",list);
    }

}
