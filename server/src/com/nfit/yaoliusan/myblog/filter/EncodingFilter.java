package com.nfit.yaoliusan.myblog.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;

		if (!req.getRequestURI().matches("/html/|/img/")) {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
}
