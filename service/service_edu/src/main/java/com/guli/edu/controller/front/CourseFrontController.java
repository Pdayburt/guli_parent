package com.guli.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.client.OrderClient;
import com.guli.edu.commonUtils.JwtUtil;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.commonUtils.orderVo.CourseWebVoOrder;
import com.guli.edu.entity.EduCourse;
import com.guli.edu.entity.frontVo.CourseFrontVo;
import com.guli.edu.entity.frontVo.CourseWebVo;
import com.guli.edu.entity.vo.ChapterVo;
import com.guli.edu.service.EduChapterService;
import com.guli.edu.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("eduservice/courseFront")
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrderClient orderClient;

    @PostMapping("getFrontCourseList/{page}/{limit}")
    public RMap getFrontCourseList(@PathVariable Long page,
                                   @PathVariable Long limit,
                                   @RequestBody(required = false)CourseFrontVo courseFrontVo){
        Page<EduCourse> eduCoursePage = new Page<EduCourse>(page, limit);
        Map<String,Object> map= eduCourseService.getCourseFrontList(eduCoursePage,courseFrontVo);
        return RMap.ok().data(map);
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public RMap getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){

        CourseWebVo courseWebVo= eduCourseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideoByCourseId(courseId);
        Boolean buyCourse = orderClient.isBuyCourse(courseId, JwtUtil.getMemberIdByJwtToken(request));
        return RMap.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }

    @PostMapping("getCourseInfoForOrder/{id}")
    public CourseWebVoOrder getCourseInfoForOrder(@PathVariable String id){
        CourseWebVo baseCourseInfo = eduCourseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }

}
