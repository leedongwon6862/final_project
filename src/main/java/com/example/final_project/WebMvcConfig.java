package com.example.final_project;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.swing.*;

@Configuration //스프링 설정 클래스 뜻함
// /image/로 시작 하는 요청을 받아 C:/web/image/ 디렉 토리 에서 해당 파일을 찾도록 Spring MVC 에 지시.
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///c:/web/image/");

    }
}




