package com.multi.contactsapp;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON)
        .favorParameter(true).parameterName("output")
        .ignoreAcceptHeader(false);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/photos/**")
			.addResourceLocations("file:/Users/inyonghong/Documents/spring_lecture/file/photos/")
			.setCacheControl(CacheControl.maxAge(Duration.ofDays(1)));
		
		registry
			.addResourceHandler("/swagger-ui/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
	        .resourceChain(false);
	}
	
	
}
