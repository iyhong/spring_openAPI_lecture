package com.multi.contactsapp.openapi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class ApiKeyInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private ApiKeyProcessor keyService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String origin = (String)request.getHeader("Origin");
		String apiKey = request.getParameter("key");
		keyService.checkApiKey(origin, apiKey);
		return true;
	}
	
}
