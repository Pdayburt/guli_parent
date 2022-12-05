package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.EduComment;
import com.guli.edu.service.EduCommentService;
import com.guli.edu.mapper.EduCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【edu_comment(评论)】的数据库操作Service实现
* @createDate 2022-12-03 05:26:12
*/
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment>
    implements EduCommentService{

}




