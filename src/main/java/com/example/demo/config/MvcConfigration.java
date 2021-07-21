package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigration implements WebMvcConfigurer {

    // @Override
    // public void addResourceHandlers( ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler("/**")
    //             .addResourceLocations("file:E:/newtaxnet/external/static/");
    // }
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:/Users/kangminseung/Desktop/code/HAHEHO_MDP/external/static/");
    }
    
}