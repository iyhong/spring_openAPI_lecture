package com.multi.contactsapp;

import java.util.Map;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SolrProxyServletConfiguration implements EnvironmentAware {
	private Map<String, String> mapProxy;

	@Bean
	public ServletRegistrationBean<ProxyServlet> servletRegistrationBean() {
		ServletRegistrationBean<ProxyServlet> bean = new ServletRegistrationBean<ProxyServlet>(new ProxyServlet(),
				mapProxy.get("servlet_url"));
		bean.addInitParameter("targetUri", mapProxy.get("target_url"));
		bean.addInitParameter("log", "false");
		return bean;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.mapProxy = Binder.get(environment).bind("proxysolr", Bindable.of(Map.class)).get();
	}
}