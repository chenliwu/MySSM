package com.charlie.ssm.demo.utils;

import java.io.*;

/**
 * Created by chenlw on 2019/06/17  13:47.
 */
public class StreamUtils {

    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }
    }

    public static void test1() throws Exception {
        String content = "{id:'',category:'教务管理系统数据库（MySql）',dataset_name:'学生信息数据集',data_json:'111'}";
        InputStream inputStream = getStringStream(content);
        System.out.println(getStreamString(inputStream));
        System.out.println(readInputStream(inputStream));
    }


    /**
     * 将字符串转化为输入流
     *
     * @param content
     * @return
     */
    public static InputStream getStringStream(String content) {
        if (content != null && !content.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(content.getBytes());
                return tInputStringStream;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    /**
     * 将输入流转化会字符串
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static String getStreamString(InputStream inputStream) throws Exception {
        if (inputStream == null) {
            return null;
        }
        BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            StringBuffer tStringBuffer = new StringBuffer();
            String sTempOneLine = new String("");
            while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                tStringBuffer.append(sTempOneLine);
            }
            return tStringBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            tBufferedReader.close();
            inputStream.close();
        }
        return null;

    }

    /**
     * 把输入流的内容转化成字符串
     *
     * @param inputStream
     * @return
     */
    public static String readInputStream(InputStream inputStream) throws Exception {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int length = 0;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            //或者用这种方法
            //byte[] result=baos.toByteArray();
            //return new String(result);
            return baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            baos.close();
            inputStream.close();
        }
    }


}
