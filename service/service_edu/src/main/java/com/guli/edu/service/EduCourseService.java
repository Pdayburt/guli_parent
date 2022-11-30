package com.guli.edu.service;

import com.guli.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.vo.CourseInfoVo;
import com.guli.edu.entity.vo.CoursePublishVo;

import java.util.List;

/**
* @author anatkh
* @description 针对表【edu_course(课程)】的数据库操作Service
* @createDate 2022-11-21 00:57:14
*/
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String courseId);

    void removeCourse(String courseId);

    List<EduCourse> getHotCourse();
}
