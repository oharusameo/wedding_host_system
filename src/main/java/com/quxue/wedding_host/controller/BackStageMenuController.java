package com.quxue.wedding_host.controller;

import com.quxue.wedding_host.constant.MenuConst;
import com.quxue.wedding_host.pojo.Menu;
import com.quxue.wedding_host.pojo.Result;
import com.quxue.wedding_host.service.MenuService;
import com.quxue.wedding_host.vo.MenuVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/backStage")
public class BackStageMenuController implements MenuConst {

    @Resource
    private MenuService menuService;

    @RequestMapping("/addBackStageMenu.do")
    public Result addMenu(@RequestBody Menu menu) {
        System.out.println("menu = " + menu);
        if (menuService.insertMenu(menu) == 1) {
            return Result.success();
        }
        return Result.error();
    }


    @RequestMapping("/showBackStageMenu.do")
    public Result showMenu(HttpServletRequest req) {
//        List<MenuVo> menuVos = menuService.selectMenuByRole(1);
        List<MenuVo> menuVos = menuService.selectMenu();
        return Result.success(menuVos);

    }

    @RequestMapping("/editBackStageMenu.do")
    public Result editMenu(@RequestBody Menu menu) {
        System.out.println("menu = " + menu);
        return Result.success(menuService.editMenu(menu));
    }

    @RequestMapping("/delBackStageMenu.do")
    public Result delMenu(@RequestBody Menu menu) {
        System.out.println("menu = " + menu);
        Integer r = menuService.delMenu(menu);
        if (Objects.equals(r, MENU_OCCUPIED_BY_ROLE)) {
            return Result.error(501, "该菜单已经有角色使用，不能删除");
        } else if (Objects.equals(r, MENU_HAS_CHILDREN)) {
            return Result.error(502, "该菜单下有子菜单，不能删除");
        }
        return Result.success("删除菜单成功");
    }


}
