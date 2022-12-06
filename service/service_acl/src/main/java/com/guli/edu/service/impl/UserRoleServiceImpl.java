package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.UserRole;
import com.guli.edu.service.UserRoleService;
import com.guli.edu.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【acl_user_role】的数据库操作Service实现
* @createDate 2022-12-05 23:16:51
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




