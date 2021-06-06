package com.multi.contactsapp.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.stereotype.Component;

@Component
public class JsonpFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String callback = null;
		String method = null;

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			callback = httpServletRequest.getParameter("callback");
			method = httpServletRequest.getMethod().toLowerCase();
		}

		if (method.equals("get") && callback != null) {
			OutputStream out = response.getOutputStream();
			out.write(String.format("/**/ %s(", callback).getBytes());
			chain.doFilter(request, response);
			out.write(new JsonPResponseWrapper((HttpServletResponse) response).getData());
			out.write(")".getBytes());
			out.close();
		} else {
			chain.doFilter(request, response);
		}
	}

	private static class JsonPResponseWrapper extends HttpServletResponseWrapper {
		private JsonPResponseWrapper(HttpServletResponse response) {
			super(response);
		}

		private byte[] getData() {
			return new ByteArrayOutputStream().toByteArray();
		}
	}
}
