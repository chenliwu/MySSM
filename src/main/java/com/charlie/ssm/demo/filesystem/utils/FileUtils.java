package com.charlie.ssm.demo.filesystem.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2018-08-28 8:55
 */
public class FileUtils {


    final static String CHARSET_UTF_8 = "UTF-8";

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


    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return     
     */
    public static boolean isFileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 判断文件是否是目录
     *
     * @param filePath
     * @return     
     */
    public static boolean isDirectoty(String filePath) {
        return new File(filePath).isDirectory();
    }

    /**
     * 读取文件的内容
     *
     * @param filePath
     * @return
     * @throws IOException     
     */
    public static String readFileContent(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("指定文件不存在：" + filePath);
        }
        if (file.isDirectory()) {
            throw new IOException("指定文件是一个文件夹：" + filePath);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET_UTF_8));
        String str;
        while (null != (str = bufferedReader.readLine())) {
            sb.append(str).append("\n");
        }
        bufferedReader.close();
        return sb.toString();
    }


    /**
     * 向指定文件写入内容
     *
     * @param parentDirPath 文件父目录
     * @param fileName      输出文件名
     * @param content       写入内容
     * @throws IOException     
     */
    public static void writerContentToFile(String parentDirPath, String fileName, String content) throws IOException {
        File parentDir = new File(parentDirPath);
        if (!parentDir.exists()) {
            throw new IOException("文件父目录不存在：" + parentDirPath);
        }
        String filePath = parentDirPath + File.separator + fileName;
        if (!isFileExists(filePath)) {
            throw new IOException("文件不存在：" + filePath);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName(CHARSET_UTF_8)));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }


}
