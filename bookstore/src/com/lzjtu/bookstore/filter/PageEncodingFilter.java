package com.lzjtu.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class PageEncodingFilter implements Filter {

	private String encoding = "UTF-8";
    protected FilterConfig filterConfig;

    public PageEncodingFilter() {
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig.getInitParameter("encoding") != null) {
            encoding = filterConfig.getInitParameter("encoding");
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(encoding);
        // String uri = request.getRequestURI();
        // String requestedUri = uri.substring(request.getContextPath().length() + 1);
        chain.doFilter(request, servletResponse);
    }
}
