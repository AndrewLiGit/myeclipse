package Util;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.briup.util.Logger;

public class LoggerIm  implements Logger{

	private String path = "src/File/log4j.properties";
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getRootLogger();
	private org.apache.log4j.Logger lo = org.apache.log4j.Logger.getLogger("A2");
	public LoggerIm(){
		PropertyConfigurator.configure(path);
	}
	
	@Override
	public void init(Properties pro) {
		this.path = pro.getProperty("path");
	}

	@Override
	public void debug(String arg0) {
		log.debug(arg0);
	}

	@Override
	public void error(String arg0) {
		log.error(arg0);
	}

	@Override
	public void fatal(String arg0) {
		log.fatal(arg0);
	}

	@Override
	public void info(String arg0) {
		lo.info(arg0);
	}

	@Override
	public void warn(String arg0) {
		lo.warn(arg0);
	}

//	public static void main(String[] args) {
//		LoggerIm li = new LoggerIm();
//		li.info("log.info");
//		li.warn("lo.warn");
//	}
	
}
