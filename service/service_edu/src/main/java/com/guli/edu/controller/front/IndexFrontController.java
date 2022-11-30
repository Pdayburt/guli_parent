package com.guli.edu.controller.front;

import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.EduCourse;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.service.EduCourseService;
import com.guli.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("eduservice/indexFront")
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;
    //查询前八条热门课程和前4条名师
    @GetMapping("index")
    private RMap index(){

        List<EduCourse> courseList = eduCourseService.getHotCourse();
        List<EduTeacher> teacherList = eduTeacherService.getHotTeacher();
        return RMap.ok().data("courseList",courseList).data("teacherList",teacherList);
    }

}
