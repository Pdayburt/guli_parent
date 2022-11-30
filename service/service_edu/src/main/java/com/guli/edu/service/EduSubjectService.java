package com.guli.edu.service;

import com.guli.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.subject.FirstSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author anatkh
* @description 针对表【edu_subject(课程科目)】的数据库操作Service
* @createDate 2022-11-19 11:28:57
*/
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<FirstSubject> getAllFirstAndSecondSubject();
}
