package com.guli.edu.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.Role;
import com.guli.edu.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/role")
//@CrossOrigin
public class RoleController {

//    @Autowired
//    private RoleService roleService;
//
//    @ApiOperation(value = "获取角色分页列表")
//    @GetMapping("{page}/{limit}")
//    public RMap index(
//            @ApiParam(name = "page", value = "当前页码", required = true)
//            @PathVariable Long page,
//
//            @ApiParam(name = "limit", value = "每页记录数", required = true)
//            @PathVariable Long limit,
//            Role role) {
//        Page<Role> pageParam = new Page<>(page, limit);
//        QueryWrapper<Role> wrapper = new QueryWrapper<>();
//        if(!StringUtils.isEmpty(role.getRoleName())) {
//            wrapper.like("role_name",role.getRoleName());
//        }
//        roleService.page(pageParam,wrapper);
//        return RMap.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
//    }
//
//    @ApiOperation(value = "获取角色")
//    @GetMapping("get/{id}")
//    public RMap get(@PathVariable String id) {
//        Role role = roleService.getById(id);
//        return RMap.ok().data("item", role);
//    }
//
//    @ApiOperation(value = "新增角色")
//    @PostMapping("save")
//    public RMap save(@RequestBody Role role) {
//        roleService.save(role);
//        return RMap.ok();
//    }
//
//    @ApiOperation(value = "修改角色")
//    @PutMapping("update")
//    public RMap updateById(@RequestBody Role role) {
//        roleService.updateById(role);
//        return RMap.ok();
//    }
//
//    @ApiOperation(value = "删除角色")
//    @DeleteMapping("remove/{id}")
//    public RMap remove(@PathVariable String id) {
//        roleService.removeById(id);
//        return RMap.ok();
//    }
//
//    @ApiOperation(value = "根据id列表删除角色")
//    @DeleteMapping("batchRemove")
//    public RMap batchRemove(@RequestBody List<String> idList) {
//        roleService.removeByIds(idList);
//        return RMap.ok();
//    }
}

