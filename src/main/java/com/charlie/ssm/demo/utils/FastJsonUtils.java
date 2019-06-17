package com.charlie.ssm.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.charlie.ssm.demo.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlw on 2019/06/17  14:14.
 */
public class FastJsonUtils {


    public static void main(String[] args) {
        testObjectToJSON();
        testMapToJSON();
        testJSONToObject();
    }

    /**
     * 测试实例对象转化成JSON字符串
     */
    public static void testObjectToJSON() {
        System.out.println("\n测试实例对象转化成JSON字符串");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setUsername("chenlw");
        userEntity.setPassword("chenlw");

        String jsonString = toJSONString(userEntity);
        System.out.println(jsonString);
    }

    /**
     * Map散列结构转化成JSON字符串
     */
    public static void testMapToJSON() {
        System.out.println("\nMap散列结构转化成JSON字符串");
        Map<String, Object> map = new HashMap<>();
        map.put("username", "chenlw");
        map.put("password", "chenlw");
        System.out.println(toJSONString(map));
    }

    /**
     * JSON字符串转化实例对象
     */
    public static void testJSONToObject() {
        System.out.println("\nJSON字符串转化实例对象");
        String jsonString = "{\"password\":\"chenlw\",\"userId\":1,\"username\":\"chenlw\"}";
        UserEntity userEntity = jsonStringToObject(jsonString, UserEntity.class);
        System.out.println(userEntity.toString());
    }


    /***************************************************************************************/


    /**
     * 对象转化成JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        return JSONObject.toJSONString(object);
    }

    /**
     * JSON字符串转化成实例对象
     *
     * @param jsonString
     * @param cla
     * @return
     */
    public static <T> T jsonStringToObject(String jsonString, Class<T> cla) {
        return JSONObject.parseObject(jsonString, cla);
    }


}
