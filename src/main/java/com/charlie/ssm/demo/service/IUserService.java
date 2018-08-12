package com.charlie.ssm.demo.service;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserEntity;

import java.util.List;

/**
 *
 */
public interface IUserService{

    UserEntity login1(UserEntity userEntity);


    /**
     * 用户登录
     * @param userEntity
     * @return
     */
    ResultEntity login(UserEntity userEntity);

    /**
     * 用户注册
     * @param userEntity
     * @return
     */
    ResultEntity register(UserEntity userEntity);

    /**
     * 删除用户
     * @param userEntity
     * @return
     */
    ResultEntity delete(UserEntity userEntity);

}
