package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.EduChapter;
import com.guli.edu.entity.EduVideo;
import com.guli.edu.entity.vo.ChapterVo;
import com.guli.edu.entity.vo.VideoVo;
import com.guli.edu.exception.GuliException;
import com.guli.edu.mapper.EduChapterMapper;
import com.guli.edu.service.EduChapterService;
import com.guli.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author anatkh
* @description 针对表【edu_chapter(课程)】的数据库操作Service实现
* @createDate 2022-11-21 01:01:38
*/
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
    implements EduChapterService{


    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        LambdaQueryWrapper<EduChapter> eduChapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduChapterLambdaQueryWrapper
                .eq(EduChapter::getCourseId,courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterLambdaQueryWrapper);
        LambdaQueryWrapper<EduVideo> eduVideoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduVideoLambdaQueryWrapper
                .eq(EduVideo::getCourseId, courseId);
        List<EduVideo> eduVideos = eduVideoService.list(eduVideoLambdaQueryWrapper);
        ArrayList<ChapterVo> chapterVoList = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            chapterVoList.add(chapterVo);
            ArrayList<VideoVo> videoVos = new ArrayList<>();
            for (int j = 0; j < eduVideos.size(); j++) {
                EduVideo eduVideo = eduVideos.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
        }
        return chapterVoList;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        LambdaQueryWrapper<EduVideo> eduVideoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduVideoLambdaQueryWrapper
                .eq(EduVideo::getChapterId,chapterId);
        int count = eduVideoService.count(eduVideoLambdaQueryWrapper);
        if (count >0) {
            throw new GuliException(20001, "有小节不能删除");
        }else {
            int res = baseMapper.deleteById(chapterId);
            return res>0;
        }
    }
}




