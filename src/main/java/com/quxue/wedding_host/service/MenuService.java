package com.quxue.wedding_host.service;

import com.quxue.wedding_host.pojo.Menu;
import com.quxue.wedding_host.vo.MenuVo;

import java.util.List;

public interface MenuService {

    Integer delMenu(Menu menu);

    Integer insertMenu(Menu menu);

    Integer editMenu(Menu menu);

    List<MenuVo> selectMenuByRole(Integer id);

    List<MenuVo> selectMenu();
}
