package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.service.EduTeacherService;
import com.guli.edu.mapper.EduTeacherMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
}




