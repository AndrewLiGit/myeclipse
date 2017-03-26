package com.briup.logger;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerInple implements Log {
	//log4j的根日志记录器
	private Logger logger = Logger.getRootLogger();
	//Logger.getLogger() 123日志记录器  
	private Map<Object,Logger> map = new HashMap<Object, Logger>();
	static {
		PropertyConfigurator.configure("src/Logger.properties");
	}
	@Override
	public void debug(String msg) {
		logger.debug(msg);
	}

	@Override
	public void debug(String msg, Object key) {
		this.getLogger(key).debug(msg);
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
	}

	@Override
	public void info(String msg, Object key) {
		this.getLogger(key).info(msg);
	}

	@Override
	public void warn(String msg) {
		logger.warn(msg);
	}

	@Override
	public void warn(String msg, Object key) {
		this.getLogger(key).warn(msg);
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
	}

	@Override
	public void error(String msg, Object key) {
		this.getLogger(key).error(msg);
	}

	public void fatal(String msg) {
		logger.fatal(msg);
	}

	public void fatal(String msg, Object key) {
		this.getLogger(key).fatal(msg);
	}
	
	private Logger getLogger(Object key){
		//key 可以是null  String  Class
		Logger logger = null;
		if (!map.containsKey(key)) {
			logger = null;
			if(key instanceof String){
				logger = Logger.getLogger(String.valueOf(key));
			}else if(key instanceof Class){
				logger = Logger.getLogger(key.getClass());
			}else if(key == null || " ".equals(key)){
				logger = Logger.getRootLogger();
			}
			map.put(key, logger);
		}else{
			return map.get(key);
		}
		return logger;
	}
}
