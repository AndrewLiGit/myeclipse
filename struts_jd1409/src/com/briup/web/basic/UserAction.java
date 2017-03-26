package com.briup.web.basic;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	public String login(){
		System.out.println("this is login");
		return "success-login";
	}
	public String register(){

		System.out.println("this is register");
		return "success-register";
	}
}
