package com.ticket.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class PortConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","DELETE","OPTIONS","PUT")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}

