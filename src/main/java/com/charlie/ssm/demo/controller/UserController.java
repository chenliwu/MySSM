package com.charlie.ssm.demo.controller;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserEntity;
import com.charlie.ssm.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenliwu
 * @create 2018-06-29 10:05
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService mIUserService;

    @RequestMapping(value = "/login1")
    @ResponseBody
    public UserEntity login1(UserEntity userEntity, Model model,HttpServletRequest request){
        UserEntity result = mIUserService.login1(userEntity);
        if(result != null){
            //携带数据带前段
            model.addAttribute("user",userEntity);
            System.out.println(result.toString());
//用户登录成功，将用户信息存储到session
            request.getSession().setAttribute("user",result);
        }
        return result;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultEntity login(UserEntity userEntity, HttpServletRequest request){
        ResultEntity resultEntity = mIUserService.login(userEntity);
        if(resultEntity.getState() == 200){
            //用户登录成功，将用户信息存储到session
            request.getSession().setAttribute("user",resultEntity.getData());
        }
        return resultEntity;
    }

}
