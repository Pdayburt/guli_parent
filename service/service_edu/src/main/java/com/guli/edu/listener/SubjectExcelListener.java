package com.guli.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guli.edu.entity.EduSubject;
import com.guli.edu.entity.excel.SubjectData;
import com.guli.edu.exception.GuliException;
import com.guli.edu.service.EduSubjectService;
import org.springframework.util.StringUtils;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(){}

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext context) {

        if (subjectData  == null) throw new GuliException(20001, "excel文件信息为空");

        EduSubject existFirstEduSubject = this.existFirstSubject(subjectData.getFirstSubjectName());
        if (existFirstEduSubject == null){
            existFirstEduSubject = new EduSubject();
            existFirstEduSubject.setTitle(subjectData.getFirstSubjectName());
            existFirstEduSubject.setParentId("0");
            eduSubjectService.save(existFirstEduSubject);
        }

        String pid = existFirstEduSubject.getId();
        EduSubject existSecondSubject = this.existSecondSubject(subjectData.getSecondSubjectName(), pid);
        if (existSecondSubject == null){
            existSecondSubject = new EduSubject();
            existSecondSubject.setTitle(subjectData.getSecondSubjectName());
            existSecondSubject.setParentId(pid);
            eduSubjectService.save(existSecondSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    private EduSubject existFirstSubject(String name){
        LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduSubjectLambdaQueryWrapper
                .eq(!StringUtils.isEmpty(name), EduSubject::getTitle,name)
                .eq(EduSubject::getParentId,0);
        return eduSubjectService.getOne(eduSubjectLambdaQueryWrapper);

    }

    private EduSubject existSecondSubject(String name ,String pid){
        LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduSubjectLambdaQueryWrapper
                .eq(!StringUtils.isEmpty(name), EduSubject::getTitle, name)
                .eq(EduSubject::getParentId, pid);
        return eduSubjectService.getOne(eduSubjectLambdaQueryWrapper);
    }
}
