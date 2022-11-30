package com.guli.edu.mapper;

import com.guli.edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.edu.entity.vo.CoursePublishVo;

/**
* @author anatkh
* @description 针对表【edu_course(课程)】的数据库操作Mapper
* @createDate 2022-11-21 00:57:14
* @Entity com.guli.edu.entity.EduCourse
*/
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String courseId);
}




