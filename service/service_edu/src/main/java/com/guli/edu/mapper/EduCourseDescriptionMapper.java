package com.guli.edu.mapper;

import com.guli.edu.entity.EduCourseDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author anatkh
* @description 针对表【edu_course_description(课程简介)】的数据库操作Mapper
* @createDate 2022-11-21 01:01:03
* @Entity com.guli.edu.entity.EduCourseDescription
*/
@Repository
public interface EduCourseDescriptionMapper extends BaseMapper<EduCourseDescription> {

}




