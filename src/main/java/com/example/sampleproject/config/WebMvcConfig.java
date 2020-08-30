//Reactからのアクセスをアプリ内の全てに適用するために作成したファイル

package com.example.sampleproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/KdiJ362/index").allowedOrigins("http://localhost:3000");
        registry.addMapping("/KdiJ362/user/{id}").allowedOrigins("http://localhost:3000");
        registry.addMapping("/KdiJ362/upload").allowedOrigins("http://localhost:3000");
        registry.addMapping("/KdiJ362/search").allowedOrigins("http://localhost:3000");
        registry.addMapping("/KdiJ362/video/{id}").allowedOrigins("http://localhost:3000");
        registry.addMapping("/KdiJ362/authenticate").allowedOrigins("http://localhost:3000");
                // .addMapping("/getBirthStoneList")
                // .allowedOrigins("http://localhost:3000");
    }
}