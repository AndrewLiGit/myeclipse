package com.briup.logger;

//ʵ����־ģ��  ��ɲ�ͬ��־������Ϣ�����
public interface Log {
	public void debug(String msg);
	//msg �����Ϣ key
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
