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
    public Result delRole(@RequestParam Integer rid) {
        Integer r = roleService.delRole(rid);
        if (r == 1) {
            return Result.success("删除角色成功");
        } else if (r == -1) {
            return Result.error(501, "该角色已被用户使用，不能删除");
        }
        return Result.error("删除角色失败");
    }

    @RequestMapping("/batchDelRole.do")
    public Result batchDelRole(@RequestBody List<Integer> roleIds) {
        Integer r = roleService.batchDelRole(roleIds);
        if (r > 0) {
            return Result.success("批量删除角色成功");
        } else if (r == -1) {
            return Result.error(501, "选中的角色已被用户使用，不能删除");
        }
        return Result.error("批量删除失败");
    }


    @RequestMapping("/editRole.do")
    public Result editRole(@RequestBody Role role) {
        System.out.println("role = " + role);

        return roleService.updateRoleMenu(role);
//        if (roleService.updateRole(role) == 1) {
//            return Result.success("更新角色信息成功");
//        }
    }

    @RequestMapping("/showRole.do")
    public Result showRole() {
        return Result.success(roleService.selectRole());
    }

    @RequestMapping("/showRoleByCondition.do")
    public Result showRoleByCondition(@RequestBody Role role) {
        return Result.success(roleService.selectRoleByCondition(role));
    }

    @RequestMapping("/showMenuIdByRole.do")
    public Result showMenuIdByRole(@RequestParam Integer rid) {
        System.out.println("rid = " + rid);
        return Result.success(roleService.selectMenuIdByRole(rid));
    }

    @RequestMapping("/editMenuInRole.do")
    public Result editMenuInRole(@RequestBody Role role) {
        System.out.println("role = " + role.getSelectedNode());

        return Result.success(role);
    }

}
