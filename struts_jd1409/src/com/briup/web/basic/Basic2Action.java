package com.briup.web.basic;

import com.opensymphony.xwork2.Action;

public class Basic2Action implements Action{

	@Override
	public String execute() throws Exception {
		System.out.println("this is Basic2Action");
		return SUCCESS;
	}

}
