package com.quxue.wedding_host;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quxue.wedding_host.mapper.MenuMapper;
import com.quxue.wedding_host.pojo.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WeddingHostApplicationTests {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testMenu() {
        QueryWrapper<Integer> wrapper = new QueryWrapper<>();
        wrapper.eq("at.rid", 1);
        List<Menu> menus = menuMapper.queryByRole(wrapper);
        System.out.println("menus = " + menus);
    }



}
