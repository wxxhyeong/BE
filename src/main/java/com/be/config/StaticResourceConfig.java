package com.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 위치 설정 (실제 경로로 수정해야 함)
        registry.addResourceHandler("/crossfit_images/**")
                .addResourceLocations("file:/C:/path/to/tomcat/webapps/crossfit_images/"); // 실제 경로로 변경
    }
}