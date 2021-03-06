package com.charlie.ssm.demo.service.impl;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.dao.UserDao;
import com.charlie.ssm.demo.entity.UserEntity;
import com.charlie.ssm.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenliwu
 * @create 2018-06-29 9:39
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao mUserDao;

    /**
     * 用户登录
     * 登录成功会返回state = 200的字段，否则就是登录失败
     * @param userEntity
     * @return
     */
    public ResultEntity login(UserEntity userEntity) {
        ResultEntity resultEntity = new ResultEntity();
        if(userEntity.getPassword() == null || userEntity.getUsername() == null){
            resultEntity.setMessage("用户名或密码为空");
            return resultEntity;
        }
        List<UserEntity> list = mUserDao.selectRecordsByParameter(userEntity);
        if(list.size() > 0){
            resultEntity.setMessage("登录成功");
            resultEntity.setState(200);
            resultEntity.setData(list.get(0));
        }else{
            resultEntity.setMessage("登录失败");
        }
        return resultEntity;
    }

    /**
     * 用户注册
     *
     * @param userEntity
     * @return
     */
    public ResultEntity register(UserEntity userEntity) {
        ResultEntity resultEntity = new ResultEntity();
        if(userEntity.getPassword() == null || userEntity.getUsername() == null){
            resultEntity.setMessage("用户名或密码为空");
            return resultEntity;
        }
        int nResult = mUserDao.insert(userEntity);
        if(nResult > 0){
            resultEntity.setState(200);
            resultEntity.setMessage("注册成功");
        }else{
            resultEntity.setMessage("注册失败");
        }
        return resultEntity;
    }

    /**
     * 删除用户
     *
     * @param userEntity
     * @return
     */
    public ResultEntity delete(UserEntity userEntity) {
        ResultEntity resultEntity = new ResultEntity();
        if(userEntity.getUserId() == null){
            resultEntity.setMessage("参数有误");
            return resultEntity;
        }
        int nResult = mUserDao.deleteByPrimaryKey(userEntity.getUserId());
        if(nResult > 0){
            resultEntity.setState(200);
            resultEntity.setMessage("删除用户成功");
        }else{
            resultEntity.setMessage("删除用户失败");
        }
        return resultEntity;
    }
}
