package com.guli.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.CrmBanner;
import com.guli.edu.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api("后台 CrmBanner CRUD")
@RestController
@RequestMapping("/educms/bannerAdmin")
public class CrmBannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("增加banner")
    @GetMapping("pageBanner/{current}/{size}")
    public RMap pageBanner (@PathVariable Long current,@PathVariable Long size){

        Page<CrmBanner> crmBannerPage = new Page<>(current, size);
        Page<CrmBanner> page = crmBannerService.page(crmBannerPage);
        return RMap.ok().data("items",page.getRecords()).data("total",page.getTotal());
    }

    @PostMapping("addBanner")
    public RMap addBanner(@RequestBody CrmBanner crmBanner){

        crmBannerService.save(crmBanner);
        return RMap.ok();
    }
    @ApiOperation("获取 Banner")
    @GetMapping("getBanner/{bannerId}")
    public RMap getBanner(@PathVariable String bannerId){

        CrmBanner crmBannerById = crmBannerService.getById(bannerId);
        return RMap.ok().data("item",crmBannerById);
    }

    @ApiOperation("修改 Banner")
    @PostMapping("updateBanner")
    public RMap updateBanner(@RequestBody CrmBanner crmBanner){

        crmBannerService.updateById(crmBanner);
        return RMap.ok();
    }

    @ApiOperation("删除 bannner")
    @DeleteMapping("deleteBanner/{bannerId}")
    public RMap deleteBanner(@PathVariable String bannerId){

        crmBannerService.removeById(bannerId);
        return RMap.error();
    }
}
