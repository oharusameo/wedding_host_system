package com.quxue.wedding_host.controller;

import com.quxue.wedding_host.pojo.Result;
import com.quxue.wedding_host.service.MenuService;
import com.quxue.wedding_host.util.CookieUtil;
import com.quxue.wedding_host.vo.MenuVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/backStage")
public class BackStageMenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/showBackStageMenu.do")
    public Result showMenu(HttpServletRequest req) {
        List<MenuVo> menuVos = menuService.selectMenuByRole(1);
        return Result.success(menuVos);

    }

}
