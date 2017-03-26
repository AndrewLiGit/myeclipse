package com.briup.logger;

//实现日志模块  完成不同日志级别信息的输出
public interface Log {
	public void debug(String msg);
	//msg 输出信息 key
	public void debug(String msg,Object key);
	public void info(String msg);
	public void info(String msg,Object key);
	public void warn(String msg);
	public void warn(String msg,Object key);
	public void error(String msg);
	public void error(String msg,Object key);
	public void fatal(String msg);
	public void fatal(String msg,Object key);
}
