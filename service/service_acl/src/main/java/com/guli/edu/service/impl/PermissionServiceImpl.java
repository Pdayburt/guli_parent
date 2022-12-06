package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.Permission;
import com.guli.edu.entity.RolePermission;
import com.guli.edu.mapper.PermissionMapper;
import com.guli.edu.service.PermissionService;
import com.guli.edu.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anatkh
 * @description 针对表【acl_permission(权限)】的数据库操作Service实现
 * @createDate 2022-12-05 23:15:26
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> queryAllMenu() {
        List<Permission> finalList = new ArrayList<>();
        List<Permission> permissionList = baseMapper.selectList(null);
        permissionList.stream().forEach(permissionNode->{
            if ("0".equals(permissionNode.getPid())){
                permissionNode.setLevel(1);
                finalList.add(recurseSearchMenu(permissionNode,permissionList));
            }
        });
        return finalList;
    }

    @Override
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        idList.add(id);
        recurseSearchChildrenId(id,idList);
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public void doAssign(String roleId, String[] permissionIds) {
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissionList);
    }

    private Permission recurseSearchMenu(Permission permissionNode, List<Permission> permissionList) {
        permissionList.stream().forEach(permission -> {
            if (permission.getPid().equals(permissionNode.getId())){
                int level  =permissionNode.getLevel()+1;
                permission.setLevel(level);
                if (permissionNode.getChildren() == null) permissionNode.setChildren(new ArrayList<Permission>());
                permissionNode.getChildren().add(recurseSearchMenu(permission,permissionList));
            }
        });
        return permissionNode;
    }

    private void recurseSearchChildrenId(String id, List<String> idList) {
        LambdaQueryWrapper<Permission> permissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        permissionLambdaQueryWrapper
                .eq(Permission::getPid,id)
                .select(Permission::getId);
        List<Permission> permissionList = baseMapper.selectList(permissionLambdaQueryWrapper);
        permissionList.stream().forEach(permission -> {
            idList.add(permission.getId());
            recurseSearchChildrenId(permission.getId(), idList);
        });
    }
}




