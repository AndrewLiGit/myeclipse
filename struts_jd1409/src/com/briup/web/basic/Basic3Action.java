package com.briup.web.basic;

import com.opensymphony.xwork2.ActionSupport;

public class Basic3Action extends ActionSupport{

	private static final long serialVersionUID = 1L;

	public String login(){
		System.out.println("this is Basic3Action login");
		return SUCCESS;
	}
	public String register(){
		
		System.out.println("this is Basic3Action register");
		return "register-success";
	}
}
