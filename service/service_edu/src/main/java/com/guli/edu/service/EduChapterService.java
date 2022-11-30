package com.guli.edu.service;

import com.guli.edu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.vo.ChapterVo;

import java.util.List;

/**
* @author anatkh
* @description 针对表【edu_chapter(课程)】的数据库操作Service
* @createDate 2022-11-21 01:01:38
*/
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    Boolean deleteChapter(String chapterId);
}
