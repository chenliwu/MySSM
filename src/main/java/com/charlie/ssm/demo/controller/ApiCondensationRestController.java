package com.charlie.ssm.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * API安全验证测试
 *
 * @author chenliwu
 * @create 2018-11-29 10:05
 **/
@RestController
@RequestMapping(value = "/api/apiCondensation")
public class ApiCondensationRestController {

    @GetMapping("/test1")
    public Map test1(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        //遍历请求里面携带的参数
        while(parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            String paramValue  = request.getParameter(parameterName);
            System.out.println(parameterName+":"+paramValue);
            result.put(parameterName,paramValue);
        }
        return result;
    }


}
