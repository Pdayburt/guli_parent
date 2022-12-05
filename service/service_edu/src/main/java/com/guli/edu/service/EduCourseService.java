package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.frontVo.CourseFrontVo;
import com.guli.edu.entity.frontVo.CourseWebVo;
import com.guli.edu.entity.vo.CourseInfoVo;
import com.guli.edu.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

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

    Map<String, Object> getCourseFrontList(Page<EduCourse> eduCoursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
