package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.Role;
import com.guli.edu.service.RoleService;
import com.guli.edu.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【acl_role】的数据库操作Service实现
* @createDate 2022-12-05 23:15:51
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




