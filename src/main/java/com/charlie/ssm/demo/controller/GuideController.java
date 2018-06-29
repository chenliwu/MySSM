package com.charlie.ssm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenliwu
 * @create 2018-06-29 15:57
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
