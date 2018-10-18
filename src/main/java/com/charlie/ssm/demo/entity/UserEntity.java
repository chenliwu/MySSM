package com.charlie.ssm.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("tb_user")
public class UserEntity {
    /**
     * 
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 
     */
    @TableField("username")
    private String username;

    /**
     * 
     */
    @TableField("password")
    private String password;

}