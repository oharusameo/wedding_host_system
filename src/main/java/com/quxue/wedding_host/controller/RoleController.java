package com.quxue.wedding_host.controller;

import com.quxue.wedding_host.pojo.Result;
import com.quxue.wedding_host.pojo.Role;
import com.quxue.wedding_host.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/backStage/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @RequestMapping("/addRole.do")
    public Result addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return Result.success("添加角色成功");
        }
        return Result.error("添加角色失败");
    }

    @RequestMapping("/delRole.do")
    public Result delRole(@RequestParam Integer id) {
        if (roleService.delRole(id) == 1) {
            return Result.success("删除角色成功");
        }
        return Result.error("删除角色失败");
    }
    @RequestMapping("/batchDelRole.do")
    public Result batchDelRole(@RequestBody List<Integer> roleIds){
        if (roleService.batchDelRole(roleIds)>0){
            return Result.success("批量删除角色成功");
        }
        return Result.error("批量删除失败");
    }


    @RequestMapping("/editRole.do")
    public Result editRole(@RequestBody Role role) {
        if (roleService.updateRole(role) == 1) {
            return Result.success("更新角色信息成功");
        }
        return Result.error("更新角色信息失败");
    }

    @RequestMapping("/showRole.do")
    public Result showRole() {
        return Result.success(roleService.selectRole());
    }

    @RequestMapping("/showRoleByCondition.do")
    public Result showRoleByCondition(@RequestBody Role role) {
        return Result.success(roleService.selectRoleByCondition(role));
    }



}
