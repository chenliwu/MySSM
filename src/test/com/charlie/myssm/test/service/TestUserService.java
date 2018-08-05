package com.charlie.myssm.test.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserEntity;
import com.charlie.ssm.demo.service.IUserService;
import com.charlie.ssm.demo.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testLogin(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("chenliwu");
        userEntity.setPassword("chenliwu");

        ResultEntity resultEntity = mUserService.login(userEntity);
        System.out.println(resultEntity.toString());
    }

    @Test
    public void testLogin1(){

    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testLikeQuery(){
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.like("username","clw");
        List<UserEntity> list = userService.selectList(wrapper);
        System.out.println("size = "+list.size());
        for(UserEntity entity:list){
            System.out.println(entity.toString());
        }
    }



}
