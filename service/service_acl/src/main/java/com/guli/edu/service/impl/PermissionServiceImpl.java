package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.entity.Permission;
import com.guli.edu.service.PermissionService;
import com.guli.edu.mapper.PermissionMapper;
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

    @Override
    public List<Permission> queryAllMenu() {
        List<Permission> permissions = baseMapper.selectList(null);
        List<Permission> resultList = buildPermission(permissions);
        return resultList;
    }

    @Override
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        selectPermissionChildById(id,idList);
        idList.add(id);
    }

    private void selectPermissionChildById(String id, List<String> idList) {
        LambdaQueryWrapper<Permission> permissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        permissionLambdaQueryWrapper
                .eq(Permission::getPid,id)
                .select(Permission::getId);
        List<Permission> childIdList = baseMapper.selectList(permissionLambdaQueryWrapper);

        childIdList.stream().forEach(item->{
            idList.add( item.getId());
            selectPermissionChildById(item.getId(), idList);
        });
    }

    public static List<Permission> buildPermission(List<Permission> permissionList) {
        List<Permission> finalList = new ArrayList<>();

        for (Permission permissionNode : permissionList) {
            if ("0".equals(permissionNode.getPid())) {
                permissionNode.setLevel(1);
                finalList.add(selectChildren(permissionNode, permissionList));
            }
        }
        return finalList;
    }

    private static Permission selectChildren(Permission permissionNode, List<Permission> permissionList) {
        for (Permission permission : permissionList) {
            if (permissionNode.getId().equals(permission.getPid())) {
                int level = permissionNode.getLevel() + 1;
                permission.setLevel(level);
                if (permissionNode.getChildren() == null) permissionNode.setChildren(new ArrayList<>());
                permissionNode.getChildren().add(selectChildren(permission, permissionList));
            }
        }
        return permissionNode;
    }
}




