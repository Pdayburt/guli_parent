package com.guli.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.EduCourse;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.service.EduCourseService;
import com.guli.edu.service.EduTeacherService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("eduservice/teacherFront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public RMap pageTeacher(@PathVariable Integer page, @PathVariable Integer limit){
        Page<EduTeacher> eduTeacherPage = new Page<EduTeacher>(page, limit);
        Map<String,Object>  teacherFrontList= eduTeacherService.getTeacherFrontList(eduTeacherPage);
        return RMap.ok().data(teacherFrontList);
    }

    @Cacheable(key = "'getTeacherFrontInfo'",value = "RMap")
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public RMap getTeacherFrontInfo(@PathVariable String teacherId){
        EduTeacher eduTeacher = eduTeacherService.getById(teacherId);
        LambdaQueryWrapper<EduCourse> eduCourseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduCourseLambdaQueryWrapper
                .eq(EduCourse::getTeacherId, teacherId);
        List<EduCourse> courseList = eduCourseService.list(eduCourseLambdaQueryWrapper);
        return RMap.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }


}
