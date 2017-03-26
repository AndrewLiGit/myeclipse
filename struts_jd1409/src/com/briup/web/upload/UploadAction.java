package com.briup.web.upload;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private String resource;
	private String resourceFileName;
	private String resourceContextType;
	
	public String execute(){
		System.out.println(resource+","+resourceFileName+","+resourceContextType);
		//获取到文件上传的目录（硬盘的绝对路径）
		String realPath = ServletActionContext.getRequest().getRealPath("/upload");
		System.out.println(realPath);
		//在该目录下创建一个文件，File,空
		File file = new File(realPath,resourceFileName);
		
		return "success";
		
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getResourceFileName() {
		return resourceFileName;
	}

	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}

	public String getResourceContextType() {
		return resourceContextType;
	}

	public void setResourceContextType(String resourceContextType) {
		this.resourceContextType = resourceContextType;
	}
	
}
