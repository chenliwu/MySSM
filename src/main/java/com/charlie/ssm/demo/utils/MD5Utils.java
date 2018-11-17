package com.charlie.ssm.demo.utils;

import java.security.MessageDigest;

/**
 * MD5工具类
 */
public class MD5Utils {

    /**
     * MD5加密实现
     * @param str
     * @return
     */
    public static String md5(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            //// 使用指定的字节更新摘要
            mdTemp.update(strTemp);
            // 获得密文
            byte[] md = mdTemp.digest();
            int j = md.length;
            // 把密文转换成十六进制的字符串形式
            char newStr[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                newStr[k++] = hexDigits[byte0 >>> 4 & 0xf];
                newStr[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(newStr);
        } catch (Exception e) {
            return null;
        }
    }

}
