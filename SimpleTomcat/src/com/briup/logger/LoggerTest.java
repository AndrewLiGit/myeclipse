package com.briup.logger;

import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

public class LoggerTest {
	static {
		//加载日志文件
		PropertyConfigurator.configure("src/logger.properties");
	}
	public static void main(String[] args) {
		//获得根日志记录器
		//SimpleLayout  级别--内容
		//HTMLLayout   HTML输出格式
		//TTCCLayout   Thread  级别  记录器类别--内容
		Logger log2 = Logger.getRootLogger();
		Logger log = Logger.getLogger("mylogger");
		log.debug("this is debug message");
		log.info("this is info message");
		log.warn("this is warn message");
		log.error("this is error message");
		log.fatal("This is a log message from the " + log.getName());
	}
}
