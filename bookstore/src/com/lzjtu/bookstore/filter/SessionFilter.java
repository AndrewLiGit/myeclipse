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

import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.model.User;
import com.lzjtu.bookstore.util.PathUtil;

public class SessionFilter implements Filter {

	String id = null;
    private String notNeedLoginPages = "";;
    protected FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        if (filterConfig.getInitParameter("notNeedLoginPages") != null) {
            notNeedLoginPages = filterConfig.getInitParameter("notNeedLoginPages");
        }
    }

    public SessionFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        System.out.println(uri + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        String requestedUri = uri.substring(request.getContextPath().length() + 2 + Constants.APP_URL_PREFIX.length());
        String id=(String)request.getParameter("id");
        System.out.println(id+"PPPPPPPPPPPPPPPP");
        
        System.out.println(uri + ">>>>>>>>>>>>>>>>>>>>>");
        String [] pages = notNeedLoginPages.split(",");
        Boolean isAllow = false;
        for (String page : pages) {
            if (page.equals(requestedUri)) {
                isAllow = true;
                break;
            }
        }
        if (isAllow) {
            chain.doFilter(request, response);
        } else {
        	if(id!=null&&id.length()>0){
            	this.id = id;
            }
            requestedUri=requestedUri+"?id="+this.id;
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER);
            if (user == null) {
                if (request.getMethod().toLowerCase().equals("get") && !requestedUri.equals("user/logout")) {
                    response.sendRedirect(PathUtil.getFullPath("user/login?go=") + requestedUri);
                } else {
                    response.sendRedirect(PathUtil.getFullPath("user/login"));
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

}