package com.quxue.wedding_host.controller;

import com.quxue.wedding_host.pojo.Result;
import com.quxue.wedding_host.pojo.User;
import com.quxue.wedding_host.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/backStage/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/showUser.do")
    public Result showUser() {
        return Result.success(userService.selectUser());
    }


    @RequestMapping("/showAllUser.do")
    public Result showAllUser() {
        return Result.success(userService.selectAllUser());
    }

    @RequestMapping("/addUser.do")
    public Result addUser(@RequestBody User user) {
        return Result.success(userService.addUser(user));
    }

    @RequestMapping("/editUser.do")
    public Result editUser(@Validated @RequestBody User user) {
        System.out.println("user = " + user);
        if (userService.editUser(user) == 1) {
            return Result.success("用户信息更新成功");
        }
        return Result.error("用户信息更新失败");
    }

    @RequestMapping("/delUser.do")
    public Result delUser(@Param("id") Integer id) {
        System.out.println("id = " + id);
        if (userService.delUserById(id) == 1) {
            return Result.success("用户删除成功");
        }
        return Result.error("异常");
    }

    @RequestMapping("/batchDel.do")
    public Result batchDelUser(@RequestBody List<Integer> userIds){
        System.out.println("userIds = " + userIds);
        if (userService.batchDelUser(userIds)>0){
            return Result.success("批量删除成功");
        }
        return Result.error("异常");
    }


}
