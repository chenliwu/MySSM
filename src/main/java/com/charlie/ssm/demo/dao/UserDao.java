package com.charlie.ssm.demo.dao;

import com.charlie.ssm.demo.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * 根据参数动态查询记录
     * @param userEntity
     * @return
     */
    List<UserEntity> selectRecordsByParameter(UserEntity userEntity);

    /**
     *
     * @mbg.generated 2018-06-28
     */
    int deleteByPrimaryKey(Long userId);

    /**
     *
     * @mbg.generated 2018-06-28
     */
    int insert(UserEntity record);

    /**
     *
     * @mbg.generated 2018-06-28
     */
    int insertSelective(UserEntity record);

    /**
     *
     * @mbg.generated 2018-06-28
     */
    UserEntity selectByPrimaryKey(Long userId);

    /**
     *
     * @mbg.generated 2018-06-28
     */
    int updateByPrimaryKeySelective(UserEntity record);

    /**
     *
     * @mbg.generated 2018-06-28
     */
    int updateByPrimaryKey(UserEntity record);
}