package com.quxue.wedding_host;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quxue.wedding_host.mapper.MenuMapper;
import com.quxue.wedding_host.mapper.UserMapper;
import com.quxue.wedding_host.pojo.Menu;
import com.quxue.wedding_host.pojo.User;
import com.quxue.wedding_host.service.MenuService;
import com.quxue.wedding_host.vo.MenuVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WeddingHostApplicationTests {

    @Autowired
    private MenuService menuService;

    @Autowired
    private  MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testMenu() {
        List<MenuVo> menuVos = menuService.selectMenuByRole(1);
        System.out.println("menuVos = " + menuVos);
    }

    @Test
    public void testUser(){
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        System.out.println("users = " + users);
    }



}
