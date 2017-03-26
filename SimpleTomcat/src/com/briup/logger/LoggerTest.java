package com.briup.logger;

import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

public class LoggerTest {
	static {
		//������־�ļ�
		PropertyConfigurator.configure("src/logger.properties");
	}
	public static void main(String[] args) {
		//��ø���־��¼��
		//SimpleLayout  ����--����
		//HTMLLayout   HTML�����ʽ
		//TTCCLayout   Thread  ����  ��¼�����--����
		Logger log2 = Logger.getRootLogger();
		Logger log = Logger.getLogger("mylogger");
		log.debug("this is debug message");
		log.info("this is info message");
		log.warn("this is warn message");
		log.error("this is error message");
		log.fatal("This is a log message from the " + log.getName());
	}
}
