package com.guli.edu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.EduSubject;
import com.guli.edu.entity.subject.FirstSubject;
import com.guli.edu.entity.subject.SecondSubject;
import com.guli.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public RMap addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return RMap.ok();
    }
    //树形结构数据
    @GetMapping("getAllSubject")
    public RMap getAllSubject(){
        List<FirstSubject> list= eduSubjectService.getAllFirstAndSecondSubject();
        return RMap.ok().data("list", list);
    }
}