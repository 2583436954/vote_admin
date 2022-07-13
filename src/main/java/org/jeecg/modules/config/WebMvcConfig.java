package org.jeecg.modules.config;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.util.ImageURL;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("拦截器="+ ImageURL.saveImageUrl);
        //将所有D:\\springboot\\pic\\ 访问都映射到/myPic/** 路径下
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+ ImageURL.saveImageUrl);
        //registry.addResourceHandler("/image/**").addResourceLocations("file:E:\\java\\image\\");

    }
}
