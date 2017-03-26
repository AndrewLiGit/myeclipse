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
	 * ��ǰ���������ص����������ʱ��,��������doFilter����
	 * ����1 : �������
	 * ����2 : ��Ӧ����
	 * ����3 : ������������
	 * ��������������:
	 *    ����һ�����������п��԰������������
	 *    ���������������������ε�ͨ�����������е�ÿһ��������,
	 *    ��ô���������е�ÿһ���������Ϳ������εĴ������������
	 *    ������������й������Ѿ�����������������,
	 *    ��ô�������������������������������Ҫ���ʵ���Դ(����˵һ��servlet������һ��ҳ�����Դ)
	 * 
	 * */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		//���ñ���
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		
		//���������Ӧ�������������е���һ�����������д���,
		//����Ѿ�û����һ����������,��ô�Ͱ�����������Ӧ������������Ҫ���ʵ���Դ
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	
	
	
	
}
