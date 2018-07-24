package com.helon.jwt.controller;

import com.alibaba.fastjson.JSONObject;
import com.helon.jwt.util.JwtHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
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
public class HelloController {


    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {

        map.put("hello", "欢迎进入HTML页面");
        return "/index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletResponse response) {
        //校验登录信息

        //生成jwt
        String token = JwtHelper.generateJWT("123", "张三");

        Map<String, Object> hashmap = new HashMap<>();
        hashmap.put("token", token);
        return JSONObject.toJSONString(hashmap);
    }

    @RequestMapping("/validateLogin")
    @ResponseBody
    public String validateLogin(String token) {
        String json = JwtHelper.validateLogin(token);

        return JSONObject.toJSONString(json);
    }
}
