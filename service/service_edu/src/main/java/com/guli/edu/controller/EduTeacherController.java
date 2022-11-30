package com.guli.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.commonUtils.RT;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.entity.vo.TeacherQuery;
import com.guli.edu.service.EduTeacherService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("讲师相关功能")
@CrossOrigin
@RestController
@RequestMapping("eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public RT<List<EduTeacher>> findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return RT.ok().data(list);
    }

    @ApiOperation("根据讲师ID删除讲师")
    @DeleteMapping("remove/{id}")
    public RT remove(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id")String  id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return RT.ok();
        }else {
            return RT.error();
        }
    }

    @ApiOperation("分页查询讲师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "当前页大小",dataType = "Integer",paramType = "query")
    })
    @GetMapping("pageTeacher")
    public RT<List<EduTeacher>> pageTeacher(@RequestParam("current") Integer current,
                                           @RequestParam("size")Integer size) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        eduTeacherService.page(eduTeacherPage);
        return RT.ok().data(eduTeacherPage.getRecords());
    }

    @ApiOperation("条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public RMap pageTeacherCondition(@PathVariable("current") Integer current,
                                     @PathVariable("size")Integer size,
                                     @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        LambdaQueryWrapper<EduTeacher> eduTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduTeacherLambdaQueryWrapper
                .like(!StringUtils.isEmpty(teacherQuery.getName()),EduTeacher::getName,teacherQuery.getName());
        eduTeacherLambdaQueryWrapper
                .eq(!StringUtils.isEmpty(teacherQuery.getLevel()), EduTeacher::getLevel, teacherQuery.getLevel());
        eduTeacherLambdaQueryWrapper
                .ge(!StringUtils.isEmpty(teacherQuery.getBegin()), EduTeacher::getGmtCreate,teacherQuery.getBegin());
        eduTeacherLambdaQueryWrapper
                .le(!StringUtils.isEmpty(teacherQuery.getEnd()), EduTeacher::getGmtModified,teacherQuery.getEnd());
        eduTeacherLambdaQueryWrapper.orderByDesc(EduTeacher::getGmtCreate);
        eduTeacherService.page(eduTeacherPage, eduTeacherLambdaQueryWrapper);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return RMap.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("增加讲师")
    @PostMapping("add")
    public RT addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return RT.ok().data(eduTeacher);
        }else {
            return RT.error().data(eduTeacher);
        }
    }

    @ApiOperation("根据讲师ID查出讲师信息")
    @GetMapping("queryById/{id}")
    public RT queryTeacherByID(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        if (eduTeacher != null) {
            return RT.ok().data(eduTeacher);
        }
        return RT.error();
    }

    @ApiOperation("根据ID修改讲师信息")
    @PostMapping("update")
    public RT update(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.updateById(eduTeacher);
        if (save) {
            return RT.ok().data(eduTeacher);
        }
        return RT.error();
    }


}
