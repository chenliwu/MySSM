package com.charlie.ssm.demo.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DataCenterClient {

    private Logger logger = LoggerFactory.getLogger(DataCenterClient.class);

    private String host = "localhost:8080";

    private String apiKey = "chenlw";
    private String apiSecret = "chenlw";

    private HttpClient httpClient;
    private InputStream in = null;

    /**
     * 连接超时时间
     */
    private int connectTimeout = 30 * 1000;
    /**
     * Socket连接超时时间
     */
    private int socketTimeout = 30 * 1000;

    /**
     * GET请求
     *
     * @param path
     * @param params
     * @return
     */
    public String get(String path, Map<String, String> params) {
        String basePath = "/v1";
        URIBuilder builder = new URIBuilder().setScheme("https")
                .setHost(host)
                .setPath(basePath + path);

        addRequiredParams("GET", path, params, apiKey, apiSecret);

        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }
        try {
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);

            RequestConfig defaultRequestConfig;


            HttpResponse resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 300) {
                System.err.println("Something wrong: " + resp.getStatusLine().toString());
            }
            BufferedReader input = new BufferedReader(new InputStreamReader(resp.getEntity().getContent(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[1000];
            int count;
            while ((count = input.read(buf)) > 0) {
                sb.append(buf, 0, count);
            }
            return sb.toString();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    void addRequiredParams(String method, String path, Map<String, String> params, String apiKey, String apiSecret) {
        params.put("key", apiKey);
        params.put("sigVer", "1");
        String ts = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").format(LocalDateTime.now());
        params.put("ts", ts);
        params.put("nonce", RandomStringUtils.randomAlphanumeric(16));
        String sig = getSig(method, path, apiSecret, params);
        params.put("sig", sig);
    }

    /**
     * 测试签名生成
     *
     * @param method
     * @param path
     * @param apiSecret
     * @param params
     * @return
     */
    public String getSig(String method, String path, String apiSecret, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Set<String> keySet = new TreeSet<>(params.keySet());
        for (String key : keySet) {
            String value = params.get(key);
            if (value == null) {
                continue;
            }
            sb.append(key);
            sb.append("=");
            sb.append(params.get(key));
            sb.append("&");
        }
        sb.setLength(sb.length() - 1); // trim the last "&"
        String unifiedString = method.toUpperCase() + ":" + path + ":" + sb.toString();
        logger.debug("unified string: " + unifiedString);

        String sig = null;
        try {
            // base64 encode the hmac
            sig = MD5Utils.md5(sb.toString());
            logger.debug("signature: " + sig);
        } catch (Exception e) {

        }
        return sig;
    }


}
