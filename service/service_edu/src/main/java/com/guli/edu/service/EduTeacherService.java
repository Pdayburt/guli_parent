package com.guli.edu.service;

import com.guli.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anatkh
* @description 针对表【edu_teacher(讲师)】的数据库操作Service
* @createDate 2022-11-14 20:28:59
*/
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> getHotTeacher();
}
