package com.helon.jwtclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.helon.jwtclient.util.JwtHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {

        map.put("hello", "客户端页面");
        return "/index";
    }


    @RequestMapping("/validateLogin")
    @ResponseBody
    public String validateLogin(String token) {
        String json = JwtHelper.validateLogin(token);

        return JSONObject.toJSONString(json);
    }
}
