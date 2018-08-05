package com.charlie.ssm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 导航Controller
 *
 * @author: chenlw
 * @date 2018/8/5  11:13
 **/
@Controller
public class GuideController {


    @RequestMapping(value = "/index")
    public  String toMainPage(){
        return "page/index";
    }

    @RequestMapping(value = "/login")
    public  String toLoginPage(){
        return "page/pages/login";
    }

}
