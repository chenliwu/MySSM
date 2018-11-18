package com.charlie.ssm.demo.filter;


import com.charlie.ssm.demo.utils.ApiRequestCertificationUtils;
import org.apache.commons.collections.map.HashedMap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 接口请求签名验证
 */
public class ApiCertificationFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("ApiCertificationFilter--------------------");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String apiSecret = "chenlw";
        if (ApiRequestCertificationUtils.checkSig(request, apiSecret)) {
            //正常跳转
            filterChain.doFilter(request, response);
        } else {
            //把请求转发到处理不合法请求的Controller
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            System.out.println("basePath=" + basePath);
            request.getRequestDispatcher("/api/apiCondensation/handleBadRequest").forward(request, response);
        }
    }

    public void destroy() {

    }
}
