package com.pavelkisliuk.fth.filter;

import com.pavelkisliuk.fth.servlet.PageType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

//@WebFilter(urlPatterns = "/jsp/TrainerAuth.jsp")
public class AuthFilter implements Filter {
	private static final String PAGE = "page";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String command = servletRequest.getParameter(PAGE);
		if(command == null) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		else {
			if (!request.getSession().isNew()) {
				System.out.println("not new");
//			String page = PageType.valueOf(command).get();
//			response.getWriter().write("{" + PAGE + ":" + page + "}");
			} else {
				System.out.println("new");
			}
		}
	}
}