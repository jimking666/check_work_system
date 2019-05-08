package com.qushihan.check_work_system.app.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index/login");
        registry.addViewController("/workManagement").setViewName("forward:/index/workManagement");
        registry.addViewController("/register").setViewName("forward:/index/register");
        registry.addViewController("/courseManagement").setViewName("forward:/index/courseManagement");
        registry.addViewController("/clazzManagement").setViewName("forward:/index/clazzManagement");
        registry.addViewController("/clazzStudentDetail").setViewName("forward:/index/clazzStudentDetail");
        registry.addViewController("/releaseWorkDetail").setViewName("forward:/index/releaseWorkDetail");
        registry.addViewController("/submitWorkDetail").setViewName("forward:/index/submitWorkDetail");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    // SpringBoot重写addResourceHandlers解决resources下面静态资源无法访问
    @Override // 对静态资源进行处理，否则boot是把所有静态资源进行拦截
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").resourceChain(true);
    }
}
