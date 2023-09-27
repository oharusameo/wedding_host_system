package com.quxue.wedding_host;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quxue.wedding_host.mapper.MenuMapper;
import com.quxue.wedding_host.mapper.RoleMapper;
import com.quxue.wedding_host.mapper.UserMapper;
import com.quxue.wedding_host.pojo.Menu;
import com.quxue.wedding_host.pojo.Role;
import com.quxue.wedding_host.pojo.User;
import com.quxue.wedding_host.service.MenuService;
import com.quxue.wedding_host.service.RoleService;
import com.quxue.wedding_host.service.UserService;
import com.quxue.wedding_host.vo.MenuVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest
class WeddingHostApplicationTests {

    @Autowired
    private MenuService menuService;

    @Autowired
    private  MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testMenu() {
        List<MenuVo> menuVos = menuService.selectMenuByRole(1);
        System.out.println("menuVos = " + menuVos);
    }

    @Test
    public void testUser(){
        User user = new User("", "11");
        List<User> users = userService.selectUserByCondition(user);
        System.out.println("users = " + users);



    }

    @Test
    public void testRole(){
        List<User> users = roleMapper.queryUsageBeforeDel(4);
        System.out.println("users = " + users);
    }


}
