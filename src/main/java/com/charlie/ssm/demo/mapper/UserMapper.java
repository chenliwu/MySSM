package com.charlie.ssm.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.charlie.ssm.demo.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据参数动态查询记录
     * @param userEntity
     * @return
     */
    List<UserEntity> selectRecordsByParameter(UserEntity userEntity);

}