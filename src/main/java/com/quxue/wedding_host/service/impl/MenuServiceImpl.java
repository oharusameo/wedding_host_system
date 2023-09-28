package com.quxue.wedding_host.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quxue.wedding_host.mapper.MenuMapper;
import com.quxue.wedding_host.mapper.RoleMapper;
import com.quxue.wedding_host.pojo.Menu;
import com.quxue.wedding_host.pojo.Role;
import com.quxue.wedding_host.service.MenuService;
import com.quxue.wedding_host.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMapper roleMapper;


    @Transactional
    @Override
    public Integer delMenu(Menu menu) {
        if (menuMapper.queryBeforeDelMenu(menu.getMenuId()).isEmpty()) {//判断该菜单是否已被使用
            if (menuMapper.queryChildrenBeforeDel(menu.getMenuId()) > 0) {//判断该菜单下有没有子菜单
                return -2;//父菜单下有子菜单
            }
            return menuMapper.deleteById(menu.getMenuId());
        }
        return -1;
    }

    @Transactional
    @Override
    public Integer insertMenu(Menu menu) {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(menu.getMenuId());
//        roleMapper.insertRoleMenu(1,list);
        return menuMapper.insert(menu);
    }

    @Transactional
    @Override
    public Integer editMenu(Menu menu) {
        return menuMapper.updateById(menu);
    }

    @Override
    public List<MenuVo> selectMenu() {
        List<Menu> list = menuMapper.selectList(new QueryWrapper<>());
        ArrayList<MenuVo> menuVos = new ArrayList<>();
        return recurMenu(list, menuVos);
    }

    @Override
    public List<MenuVo> selectMenuByRole(Integer id) {
        QueryWrapper<Integer> wrapper = new QueryWrapper<>();
        wrapper.eq("at.aid", id);
        List<Menu> list = menuMapper.queryByRole(wrapper);
        List<MenuVo> menuVos = new ArrayList<>();
        return recurMenu(list, menuVos);
    }


    private List<MenuVo> recurMenu(List<Menu> list, List<MenuVo> menuVos) {
        for (Menu menu : list) {
            Integer parentId = menu.getParentId();
            if (parentId == 0) { //如果菜单parentId为0，则为父级菜单
                Integer parentMenuId = menu.getMenuId();
                MenuVo parentMenu = new MenuVo();
                parentMenu.setId(menu.getMenuId());
                parentMenu.setLabel(menu.getMenuName());
                parentMenu.setMdesc(menu.getMdesc());

                //循环添加子菜单
                for (Menu subs : list) {
                    if (Objects.equals(subs.getParentId(), parentMenuId)) {
                        MenuVo subMenu = new MenuVo();
                        subMenu.setId(subs.getMenuId());
                        subMenu.setLabel(subs.getMenuName());
                        subMenu.setUrl(subs.getUrl());
                        subMenu.setMdesc(subs.getMdesc());
                        subMenu.setParentId(subs.getParentId());
                        parentMenu.getChildren().add(subMenu);//在父菜单下添加子菜单对象
                    }
                }
                menuVos.add(parentMenu);
            }

            if (list.remove(menu)) {//每次递归将菜单对象从list里移除，如果移除返回false,则遍历完成
                recurMenu(list, menuVos);
            }
            return menuVos;
        }
        return null;
    }
}
