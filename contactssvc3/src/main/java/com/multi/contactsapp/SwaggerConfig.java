package com.multi.contactsapp;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.any())              
		          .paths(PathSelectors.ant("/contacts/**"))                          
		          .build()
		          .apiInfo(apiInfo());    
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("연락처 서비스 ", 
		          "RESTful Service 학습을 위해 만든 서비스 API 입니다.", 
		          "v1.0", 
		          "Terms of service", 
		          new Contact(
	        		  	"홍인용", 
	        		  	"http://opensg.net", 
	        		  	"stepanowon@opensg.net"), 
		          "MIT License", "http://opensg.net/license.html", Collections.emptyList()
		          );
	}
}
