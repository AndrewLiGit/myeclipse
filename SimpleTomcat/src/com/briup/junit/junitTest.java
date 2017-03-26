package com.briup.junit;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.briup.server.SeverMain;

public class junitTest {
	@Before
	public void start(){
		PropertyConfigurator.configure("src/logger.properties");
	}
//	@Test
//	public void test(){
//		Logger log = Logger.getLogger("mylogger");
//		log.debug("this is debug message");
//		log.info("this is info message");
//		log.warn("this is warn message");
//		log.error("this is error message");
//		log.fatal("this is fatal message");
//	}
	@Test
	public void test(){
		new SeverMain().startServer();
	}
	@After
	public void end(){
		System.out.println("end!");
	}
}
