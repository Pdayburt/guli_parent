package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.RolePermission;
import com.guli.edu.service.RolePermissionService;
import com.guli.edu.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【acl_role_permission(角色权限)】的数据库操作Service实现
* @createDate 2022-12-05 23:16:11
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




