package com.lovezz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lovezz.entity.TbMenu;
import com.lovezz.service.TbMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: liweijian
 * @Date: 2019/12/6 19:57
 * @Description:
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private TbMenuService menuService;

    @RequestMapping("/toMenu")
    public ModelAndView toMenu(ModelAndView modelAndViewd){
        List<TbMenu> menuList = menuService.selectList(new EntityWrapper<TbMenu>().eq("isDelete", "0").eq("type","1"));

        modelAndViewd.addObject("menuList",menuList);
        modelAndViewd.setViewName("menu/index");
        return modelAndViewd;
    }

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
