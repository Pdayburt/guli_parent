package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.CrmBanner;
import com.guli.edu.service.CrmBannerService;
import com.guli.edu.mapper.CrmBannerMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author anatkh
* @description 针对表【crm_banner(首页banner表)】的数据库操作Service实现
* @createDate 2022-11-27 06:29:58
*/
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner>
    implements CrmBannerService{

    @Cacheable(value = "List<CrmBanner>",key = "'selectBanner'")
    @Override
    public List<CrmBanner> selectBanner() {
        LambdaQueryWrapper<CrmBanner> crmBannerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        crmBannerLambdaQueryWrapper
                .orderByDesc(CrmBanner::getId)
                .last("limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(crmBannerLambdaQueryWrapper);
        return crmBanners;
    }
}




