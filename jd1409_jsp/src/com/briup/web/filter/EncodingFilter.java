package com.briup.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		
	}
	
	
	/*
	 * 当前过滤器拦截到本次请求的时候,会调用这个doFilter方法
	 * 参数1 : 请求对象
	 * 参数2 : 响应对象
	 * 参数3 : 过滤器链对象
	 * 过滤器链的作用:
	 *    首先一个过滤器链中可以包含多个过滤器
	 *    过滤器链可以让请求依次的通过过滤器链中的每一个过滤器,
	 *    那么过滤器链中的每一个过滤器就可以依次的处理这个请求了
	 *    如果过滤器链中过滤器已经都处理过这个请求了,
	 *    那么过滤器链把这个请求交这个请求这次真正要访问的资源(比如说一个servlet或者是一个页面等资源)
	 * 
	 * */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		//设置编码
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		
		//把请求和响应交给过滤器链中的下一个过滤器进行处理,
		//如果已经没有下一个过滤器了,那么就把这个请求和响应交给它们真正要访问的资源
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	
	
	
	
}
