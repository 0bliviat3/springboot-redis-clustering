package com.sample.demo.config;

import com.sample.demo.interceptor.SampleInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SampleInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/**", "/sign/**", "/js/**", "/css/**");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.debug("add cors config");
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8081", "http://localhost:80") //개발용 소스
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
