package com.charlie.myssm.test.service;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserEntity;
import com.charlie.ssm.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 单元测试
 * @author chenliwu
 * @create 2018-06-29 10:51
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springMVC-config.xml"})
@WebAppConfiguration
public class TestUserService {

    @Autowired
    private IUserService mUserService;

    @Test
    public void testLogin(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("chenliwu");
        userEntity.setPassword("chenliwu");

        ResultEntity resultEntity = mUserService.login(userEntity);
        System.out.println(resultEntity.toString());
    }


}
