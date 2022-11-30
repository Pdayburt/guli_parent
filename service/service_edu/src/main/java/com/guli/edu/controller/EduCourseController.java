package com.guli.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.EduCourse;
import com.guli.edu.entity.enums.CourseStatus;
import com.guli.edu.entity.vo.CourseInfoVo;
import com.guli.edu.entity.vo.CoursePublishVo;
import com.guli.edu.entity.vo.CourseQuery;
import com.guli.edu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @DeleteMapping("removeById/{courseId}")
    public RMap removeById(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return RMap.ok();
    }

    @PostMapping("getCourseByCondition/{current}/{size}")
    public RMap getCourseByCondition(@PathVariable Integer current,
                                     @PathVariable Integer size,
                                     @RequestBody CourseQuery courseQuery){
        Page<EduCourse> eduCoursePage = new Page<>(current, size);
        LambdaQueryWrapper<EduCourse> eduCourseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduCourseLambdaQueryWrapper
                .like(!StringUtils.isEmpty(courseQuery.getTitle()),EduCourse::getTitle,courseQuery.getTitle());
        eduCourseLambdaQueryWrapper
                .eq(!StringUtils.isEmpty(courseQuery.getStatus()),EduCourse::getStatus,courseQuery.getStatus());

        Page<EduCourse> eduCoursePageRes = eduCourseService.page(eduCoursePage, eduCourseLambdaQueryWrapper);
        List<EduCourse> records = eduCoursePageRes.getRecords();
        long total = eduCoursePageRes.getTotal();

        return RMap.ok().data("total",total).data("rows",records);

    }

    @GetMapping("getCourseList")
    public RMap getCourseList(){
        List<EduCourse> list = eduCourseService.list();
        return RMap.ok().data("list",list);
    }

    @PostMapping("/addCourseInfo")
    public RMap addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return RMap.ok().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public RMap getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo =  eduCourseService.getCourseInfo(courseId);
        return RMap.ok().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public RMap updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return RMap.ok();
    }

    @GetMapping("getPublishCourseInfo/{courseId}")
    public RMap getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(courseId);
        return RMap.ok().data("coursePublishVo",coursePublishVo);
    }

    @PostMapping("publishCourse/{courseId}")
    public RMap publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus(CourseStatus.Normal.value());
        eduCourseService.updateById(eduCourse);
        return RMap.ok();
    }

}
