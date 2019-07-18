package com.charlie.ssm.demo.utils;

import java.security.MessageDigest;

/**
 * MD5工具类
 */
public class MD5Utils {



    public static void main(String[]args){
        try{
            String testString = "12345678";
            System.out.println("MD5:"+md5(testString));
            System.out.println("md5Encode:"+md5Encode(testString.getBytes()));
        }catch (Exception e){
            System.out.println("MD5加密失败："+e.getMessage());
        }

    }


    /**
     * MD5加密实现
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
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

    private static final char[] hexadecimal = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5Encode(byte[] binaryData) {
        if (binaryData.length != 16) {
            return null;
        } else {
            char[] buffer = new char[32];

            for(int i = 0; i < 16; ++i) {
                int low = binaryData[i] & 15;
                int high = (binaryData[i] & 240) >> 4;
                buffer[i * 2] = hexadecimal[high];
                buffer[i * 2 + 1] = hexadecimal[low];
            }

            return new String(buffer);
        }
    }



}
