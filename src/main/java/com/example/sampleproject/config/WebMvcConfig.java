//Reactからのアクセスをアプリ内の全てに適用するために作成したファイル

package com.example.sampleproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/index").allowedOrigins("http://localhost:3000");
        registry.addMapping("/user/{id}").allowedOrigins("http://localhost:3000");
        registry.addMapping("/upload").allowedOrigins("http://localhost:3000");
        registry.addMapping("/search").allowedOrigins("http://localhost:3000");
        registry.addMapping("/video/{id}").allowedOrigins("http://localhost:3000");
                // .addMapping("/getBirthStoneList")
                // .allowedOrigins("http://localhost:3000");
    }
}