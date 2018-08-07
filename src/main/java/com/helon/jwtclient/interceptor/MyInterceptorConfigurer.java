package com.helon.jwtclient.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: Helon
 * @Description:
 * @Data: Created in 2018/7/26 17:27
 * @Modified By:
 */
@Configuration
public class MyInterceptorConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
