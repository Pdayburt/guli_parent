package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.service.EduTeacherService;
import com.guli.edu.mapper.EduTeacherMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
* @createDate 2022-11-14 20:28:59
*/
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
    implements EduTeacherService{

    @Cacheable(key = "'getHotTeacher'",value = "List<EduTeacher>")
    @Override
    public List<EduTeacher> getHotTeacher() {

        LambdaQueryWrapper<EduTeacher> eduTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduTeacherLambdaQueryWrapper
                .orderByDesc(EduTeacher::getGmtCreate)
                .last("limit 4");
        return baseMapper.selectList(eduTeacherLambdaQueryWrapper);
    }

    @Cacheable(key = "'getTeacherFrontList'",value = "Map<String, Object>")
    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> eduTeacherPage) {
        LambdaQueryWrapper<EduTeacher> eduTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduTeacherLambdaQueryWrapper
                .orderByDesc(EduTeacher::getId);
        baseMapper.selectPage(eduTeacherPage, eduTeacherLambdaQueryWrapper);

        long total = eduTeacherPage.getTotal();
        long pages = eduTeacherPage.getPages();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        long current = eduTeacherPage.getCurrent();
        long size = eduTeacherPage.getSize();
        boolean hasPrevious = eduTeacherPage.hasPrevious();
        boolean hasNext = eduTeacherPage.hasNext();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total",total);
        hashMap.put("pages",pages);
        hashMap.put("records",records);
        hashMap.put("current",current);
        hashMap.put("size",size);
        hashMap.put("hasPrevious",hasPrevious);
        hashMap.put("hasNext",hasNext);
        return hashMap;
    }
}




