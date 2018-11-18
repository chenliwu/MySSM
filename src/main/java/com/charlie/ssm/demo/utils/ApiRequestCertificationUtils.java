package com.charlie.ssm.demo.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;

public class ApiRequestCertificationUtils {


    /**
     * 检查API签名是否合法
     * （1）客户端请求里面会携带签名（客户端利用apiSecret和给定的算法产生签名）
     * （2）服务器端会使用存在服务器端的apiSecret和相同的算法产生一个签名。
     * （3）服务器端对这两个签名进行校验，得出签名的有效性。如果有效，则正常走业务流程，否则拒绝请求。
     *
     * @param request
     * @param apiSecret
     * @return
     */
    public static boolean checkSig(HttpServletRequest request, String apiSecret) {

        ///showTreeSet(request);

        //请求方携带的API签名
        String clientSig = null;
        StringBuilder sb = new StringBuilder();

        Enumeration<String> parameterNames = request.getParameterNames();
        Set<String> keySet = new TreeSet<>();
        //讲请求参数的name按顺序排列
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (parameterName.equals("sig")) {
                //获取客户端的API签名
                clientSig = request.getParameter("sig");
                continue;
            }
            keySet.add(parameterName);
        }
        //遍历有顺序的请求参数，以便在服务器生成签名
        for (String key : keySet) {
            String value = request.getParameter(key);
            if (value == null) {
                continue;
            }
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        }
        sb.setLength(sb.length() - 1); // trim the last "&"
        System.out.println("\n参数名称和参数值链接成一个字符串A:"+sb.toString()+"\n");


        //服务器端根据参数生成的签名
        String serverSig = null;
        try {
            //Base64解码客户端的签名
            clientSig = new String(Base64.getDecoder().decode(clientSig), "UTF-8");
            System.out.println("\n客户端的API签名（clientSig）:" + clientSig);

            ///在参数字符串A的首尾加上apiSecret组成一个新字符串B
            StringBuilder sb1 = new StringBuilder();
            sb1.append(apiSecret).append(sb).append(apiSecret);

            //对新字符串B进行MD5加密，加密后与Base64解码后的客户端签名进行验证
            serverSig = MD5Utils.md5(sb1.toString());
            System.out.println("服务器端生成的API签名（serverSig）:" + serverSig);
        } catch (Exception e) {
            System.out.println("异常："+e.getMessage());
        }
        return clientSig != null && serverSig != null && clientSig.equals(serverSig);
    }


    /**
     * 显示请求参数
     * @param request
     */
    private static void showTreeSet(HttpServletRequest request){
        System.out.println("showTreeSet---------------");
        Enumeration<String> parameterNames = request.getParameterNames();
        Set<String> keySet = new TreeSet<>();
        //遍历请求里面携带的参数,放到map里面
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (parameterName.equals("sig")) {
                continue;
            }
            keySet.add(parameterName);
        }
        for(String key:keySet){
            String value=request.getParameter(key);
            System.out.println(key+":"+value);
        }
        System.out.println("showTreeSet---------------");
    }

}
