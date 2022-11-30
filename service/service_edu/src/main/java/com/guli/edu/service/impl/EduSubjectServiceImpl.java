package com.guli.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.EduSubject;
import com.guli.edu.entity.excel.SubjectData;
import com.guli.edu.entity.subject.FirstSubject;
import com.guli.edu.entity.subject.SecondSubject;
import com.guli.edu.listener.SubjectExcelListener;
import com.guli.edu.service.EduSubjectService;
import com.guli.edu.mapper.EduSubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
* @author anatkh
* @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
* @createDate 2022-11-19 11:28:57
*/
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
    implements EduSubjectService{

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FirstSubject> getAllFirstAndSecondSubject() {
        LambdaQueryWrapper<EduSubject> eduSubjectFirstWrapper = new LambdaQueryWrapper<>();
        eduSubjectFirstWrapper
                .eq(EduSubject::getParentId, '0');
        List<EduSubject> firstSubjectList = baseMapper.selectList(eduSubjectFirstWrapper);
        LambdaQueryWrapper<EduSubject> eduSubjectSecondWrapper = new LambdaQueryWrapper<>();
        eduSubjectSecondWrapper
                .ne(EduSubject::getParentId, '0');
        List<EduSubject> secondSubjectList = baseMapper.selectList(eduSubjectSecondWrapper);
        List<FirstSubject> finalSubjectList = new ArrayList<>();
        for (int i = 0; i < firstSubjectList.size(); i++) {
            EduSubject eduSubject = firstSubjectList.get(i);
            FirstSubject firstSubject = new FirstSubject();
            BeanUtils.copyProperties(eduSubject, firstSubject);
            finalSubjectList.add(firstSubject);
            ArrayList<SecondSubject> secondSubjects = new ArrayList<>();
            for (int i1 = 0; i1 < secondSubjectList.size(); i1++) {
                EduSubject eduSubject1 = secondSubjectList.get(i1);
                if (eduSubject1.getParentId().equals(eduSubject.getId()) ){
                    SecondSubject secondSubject = new SecondSubject();
                    BeanUtils.copyProperties(eduSubject1,secondSubject);
                    secondSubjects.add(secondSubject);
                }
            }
            firstSubject.setChildren(secondSubjects);
        }
        return finalSubjectList;
    }
}




