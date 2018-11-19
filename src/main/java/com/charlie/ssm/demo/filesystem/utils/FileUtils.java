package com.charlie.ssm.demo.filesystem.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2018-08-28 8:55
 */
public class FileUtils {

    /**
     * 判断文件是否为图片<br>
     * <br>
     * @param pInput 文件名<br>
     * @param pImgeFlag 判断具体文件类型<br>
     * @return 检查后的结果<br>
     * @throws Exception
     */
    public static boolean isPicture(String  pInput,
                                    String pImgeFlag) throws Exception{
        // 文件名称为空的场合
        if(pInput == null || pInput.length() == 0){
            // 返回不和合法
            return false;
        }
        // 获得文件后缀名
        String tmpName = pInput.substring(pInput.lastIndexOf(".") + 1,
                pInput.length());
        // 声明图片后缀名数组
        String imgeArray [][] = {
                {"bmp", "0"}, {"dib", "1"}, {"gif", "2"},
                {"jfif", "3"}, {"jpe", "4"}, {"jpeg", "5"},
                {"jpg", "6"}, {"png", "7"} ,{"tif", "8"},
                {"tiff", "9"}, {"ico", "10"}
        };
        // 遍历名称数组
        for(int i = 0; i<imgeArray.length;i++){
            // 判断单个类型文件的场合
            if((pInput != null && pInput.length() != 0)
                    && imgeArray [i][0].equals(tmpName.toLowerCase())
                    && imgeArray [i][1].equals(pImgeFlag)){
                return true;
            }
            // 判断符合全部类型的场合
            if((pInput != null && pInput.length() != 0)
                    && imgeArray [i][0].equals(tmpName.toLowerCase())){
                return true;
            }
        }
        return false;
    }


    /**
     * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
     *
     * @param imageFile
     * @return
     */
    public static boolean isImage(File imageFile) {
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }



}
