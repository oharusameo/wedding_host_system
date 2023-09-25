package com.quxue.wedding_host.service;

import com.quxue.wedding_host.vo.MenuVo;

import java.util.List;

public interface MenuService {
    List<MenuVo> selectMenuByRole(Integer id);
}
