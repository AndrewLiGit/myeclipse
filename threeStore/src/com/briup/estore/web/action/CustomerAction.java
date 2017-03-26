package com.briup.estore.web.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.briup.estore.bean.Customer;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.service.impl.CustomerServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 处理顾客请求的类
 * */
public class CustomerAction  {
	private Customer customer;
	
	private String valueStackStr;
	/**
	 * 注册
	 * 将用户信息持久化到数据库中
	 * */
	private ICustomerService customerService = 
			new CustomerServiceImpl();
	
	public String register(){
		customerService.register(customer);
		return "register-success";
	}
	/**
	 * 登录
	 * */
	public String login(){
		System.out.println(customer.getUsername()+"--"+customer.getPassword());
		//调用service代码完成登录
		Customer cus 
			= customerService.login(customer.getUsername(), customer.getPassword());
		
		if(cus != null){
			System.out.println("登录成功");
			valueStackStr = "登录成功——123；；；";
			
			//将用户信息放入到session中
			//获取session
			/*Map<String, Object> session =
					ActionContext.getContext().getSession();
			session.put("cus", cus);*/
			HttpSession session = ServletActionContext.getRequest().getSession();
			
			String ss = ServletActionContext.getRequest().getParameter("test");
			System.out.println(ss+"==request");
			session.setAttribute("cus", cus);
		}
		return "login-success";
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getValueStackStr() {
		return valueStackStr;
	}
	public void setValueStackStr(String valueStackStr) {
		this.valueStackStr = valueStackStr;
	}
}
