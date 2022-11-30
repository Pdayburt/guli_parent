package com.guli.edu.controller;

import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.EduChapter;
import com.guli.edu.entity.vo.ChapterVo;
import com.guli.edu.service.EduChapterService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public RMap getChapterVideo(@PathVariable("courseId") String courseId) {

        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return RMap.ok().data("allChapterVideo",list);
    }

    @PostMapping("addChapter")
    public RMap addChapter(@RequestBody EduChapter eduChapter) {

        eduChapterService.save(eduChapter);
        return RMap.ok();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public RMap getChapterInfo(@PathVariable String chapterId) {

        EduChapter chapterById = eduChapterService.getById(chapterId);
        return RMap.ok().data("chapter",chapterById);
    }

    @PostMapping("updateChapter")
    public RMap updateChapter(@RequestBody EduChapter eduChapter) {

        boolean b = eduChapterService.updateById(eduChapter);
        return RMap.ok();
    }

    @DeleteMapping("deleteChapter/{chapterId}")
    public RMap deleteChapter(@PathVariable String chapterId) {

        Boolean flag = eduChapterService.deleteChapter(chapterId);
        if (flag) {
            return RMap.ok();
        }else {
            return RMap.error();
        }

    }

}
