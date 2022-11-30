package com.guli.edu.service;

import com.guli.edu.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anatkh
* @description 针对表【crm_banner(首页banner表)】的数据库操作Service
* @createDate 2022-11-27 06:29:58
*/
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectBanner();
}
