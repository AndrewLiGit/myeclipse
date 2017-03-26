package com.lzjtu.bookstore.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzjtu.bookstore.AppContext;
import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.model.User;

public class AppContextFilter implements Filter {

    public AppContextFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String requestedUri = uri.substring(request.getContextPath().length() + 1);
        AppContext.setContextPath(request.getContextPath());

        if (requestedUri.endsWith(".js") || requestedUri.endsWith(".css") || requestedUri.endsWith(".jpg") || requestedUri.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }

        AppContext appContext = AppContext.getAppContext();
        appContext.addObject(Constants.APP_CONTEXT_REQUEST, request);
        appContext.addObject(Constants.APP_CONTEXT_RESPONSE, response);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        appContext.addObject(Constants.APP_CONTEXT_USER, user);
        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);
        try {
            chain.doFilter(request, response);
        } finally {
            appContext.clear();
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
