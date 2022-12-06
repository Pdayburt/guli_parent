package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.User;
import com.guli.edu.service.UserService;
import com.guli.edu.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【acl_user(用户表)】的数据库操作Service实现
* @createDate 2022-12-05 23:16:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




