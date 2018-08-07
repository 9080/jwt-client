package com.helon.jwtclient.filter;

import com.alibaba.fastjson.JSONObject;
import com.helon.jwtclient.util.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Helon
 * @Description:
 * @Data: Created in 2018/7/26 14:47
 * @Modified By:
 */
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("===============过滤器 前=============");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String refresh = JwtHelper.validateLogin(httpServletRequest.getHeader("User-Token"));
        logger.info(refresh);
        if (StringUtils.isNotBlank(refresh)) {
            httpServletResponse.addHeader("User-Token", JSONObject.parseObject(refresh).getString("freshToken"));
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
        logger.info("===============过滤器 后=============");
    }

    @Override
    public void destroy() {

    }
}
