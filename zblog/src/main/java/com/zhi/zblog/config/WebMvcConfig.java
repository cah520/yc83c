package com.zhi.zblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhi.zblog.web.LoginInterceptor;

//我们接管了webmvc，自动配置将会失效
//使用@Configuration会将当前配置类加载到容器中
//@Configuration   
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration  ir=registry.addInterceptor(new LoginInterceptor());
		/**
		 * 互联网项目拦截少数请求
		 * 企业内部网页拦截多数请求
		 */
		ir.addPathPatterns("/addArticle.do");
	}
  
	
	
}
