package com.helon.jwtclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.helon.jwtclient.util.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Helon
 * @Description:
 * @Data: Created in 2018/7/18 9:06
 * @Modified By:
 */
@Controller
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {

        map.put("hello", "客户端页面");
        return "/index";
    }

    /**
     * @Summary TODO
     * @Description TODO
     * @Author helon
     * @Date 2018/8/7 14:14
     * @Param [request, response]
     * @return java.lang.String
     **/
    @RequestMapping("/validateLogin")
    @ResponseBody
    public String validateLogin(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("User-Token");
        String json = JwtHelper.validateLogin(token);
        return JSONObject.toJSONString(json);
    }
}
