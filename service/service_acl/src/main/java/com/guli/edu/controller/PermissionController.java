package com.guli.edu.controller;



import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.Permission;
import com.guli.edu.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("queryAllMenu")
    public RMap queryAllMenu(){
        List<Permission> list = permissionService.queryAllMenu();
        return RMap.ok().data("permission", list);
    }

    @DeleteMapping("remove/{id}")
    public RMap remove(@PathVariable String id){
        permissionService.removeChildById(id);
        return RMap.ok();
    }

    @PostMapping("doAssign")
    public RMap doAssign(String roleId,String[] permissionIds){
        permissionService.doAssign(roleId,permissionIds);
        return RMap.ok();
    }



//    @Autowired
//    private PermissionService permissionService;
//
//    //获取全部菜单
//    @ApiOperation(value = "查询所有菜单")
//    @GetMapping
//    public RMap indexAllPermission() {
//        List<Permission> list =  permissionService.queryAllMenuGuli();
//        return RMap.ok().data("children",list);
//    }
//
//    @ApiOperation(value = "递归删除菜单")
//    @DeleteMapping("remove/{id}")
//    public RMap remove(@PathVariable String id) {
//        permissionService.removeChildByIdGuli(id);
//        return RMap.ok();
//    }
//
//    @ApiOperation(value = "给角色分配权限")
//    @PostMapping("/doAssign")
//    public RMap doAssign(String roleId,String[] permissionId) {
//        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
//        return RMap.ok();
//    }
//
//    @ApiOperation(value = "根据角色获取菜单")
//    @GetMapping("toAssign/{roleId}")
//    public RMap toAssign(@PathVariable String roleId) {
//        List<Permission> list = permissionService.selectAllMenu(roleId);
//        return RMap.ok().data("children", list);
//    }
//
//
//
//    @ApiOperation(value = "新增菜单")
//    @PostMapping("save")
//    public RMap save(@RequestBody Permission permission) {
//        permissionService.save(permission);
//        return RMap.ok();
//    }
//
//    @ApiOperation(value = "修改菜单")
//    @PutMapping("update")
//    public RMap updateById(@RequestBody Permission permission) {
//        permissionService.updateById(permission);
//        return RMap.ok();
//    }

}

