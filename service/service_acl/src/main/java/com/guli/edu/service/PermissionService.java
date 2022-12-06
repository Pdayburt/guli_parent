package com.guli.edu.service;

import com.guli.edu.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anatkh
* @description 针对表【acl_permission(权限)】的数据库操作Service
* @createDate 2022-12-05 23:15:26
*/
public interface PermissionService extends IService<Permission> {

    List<Permission> queryAllMenu();

    void removeChildById(String id);

}
