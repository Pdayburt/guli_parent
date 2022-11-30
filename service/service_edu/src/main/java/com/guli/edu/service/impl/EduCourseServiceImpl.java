package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.client.VodClient;
import com.guli.edu.entity.EduChapter;
import com.guli.edu.entity.EduCourse;
import com.guli.edu.entity.EduCourseDescription;
import com.guli.edu.entity.EduVideo;
import com.guli.edu.entity.vo.CourseInfoVo;
import com.guli.edu.entity.vo.CoursePublishVo;
import com.guli.edu.exception.GuliException;
import com.guli.edu.mapper.EduCourseDescriptionMapper;
import com.guli.edu.mapper.EduVideoMapper;
import com.guli.edu.service.EduChapterService;
import com.guli.edu.service.EduCourseService;
import com.guli.edu.mapper.EduCourseMapper;
import com.guli.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【edu_course(课程)】的数据库操作Service实现
* @createDate 2022-11-21 00:57:14
*/
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
    implements EduCourseService{

    @Autowired
    private EduCourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private VodClient vodClient;


    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0){
            throw new GuliException(20001, "添加Course失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        String id = eduCourse.getId();
        eduCourseDescription.setId(id);
        courseDescriptionMapper.insert(eduCourseDescription);
        return id;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription eduCourseDescription = courseDescriptionMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i == 0){
            throw  new GuliException(20001, "修改course表失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionMapper.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    @Transactional
    public void removeCourse(String courseId) {
        courseDescriptionMapper.deleteById(courseId);
        LambdaQueryWrapper<EduChapter> eduChapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduChapterLambdaQueryWrapper
                .eq(EduChapter::getCourseId,courseId);
        eduChapterService.remove(eduChapterLambdaQueryWrapper);
        LambdaQueryWrapper<EduVideo> eduVideoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduVideoLambdaQueryWrapper
                .eq(EduVideo::getCourseId,courseId);
        List<EduVideo> list = eduVideoService.list(eduVideoLambdaQueryWrapper);
        List<String> videoIds = list.stream().filter(eduVideo -> !StringUtils.isEmpty(eduVideo.getVideoSourceId())).map(eduVideo -> eduVideo.getVideoSourceId()).collect(Collectors.toList());
        if (videoIds.size()>0){
            vodClient.deleteBatch(videoIds);
        }
        eduVideoService.remove(eduVideoLambdaQueryWrapper);

        int i = baseMapper.deleteById(courseId);
        if (i == 0){
            throw new GuliException(20001, "删除Course失败");
        }
    }

    @Cacheable(value = "List<EduCourse>",key = "'List<EduCourse>'")
    @Override
    public List<EduCourse> getHotCourse() {
        LambdaQueryWrapper<EduCourse> eduCourseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduCourseLambdaQueryWrapper
                .orderByDesc(EduCourse::getGmtCreate)
                .last("limit 8");
        return baseMapper.selectList(eduCourseLambdaQueryWrapper);
    }
}




