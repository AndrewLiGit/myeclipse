package Util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

import com.briup.util.Logger;

public class LoggerImpl implements Logger{

	private String cofigPath = "src/File/woss.log";
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("LoggerImpl");
	private String Lev = "info";
	public LoggerImpl(){
		Level leve = Level.toLevel(Lev);
		PatternLayout pattern = new PatternLayout();
		pattern.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss},(%p)=%m%n");
		ConsoleAppender console = null;
		FileAppender fileA = null;
		try {
			console = new ConsoleAppender(pattern);
			fileA = new FileAppender(pattern, cofigPath, true);
			log.addAppender(fileA);
			log.addAppender(console);
			log.setLevel(leve);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(Properties pro) {
		this.cofigPath = pro.getProperty("cofigPath");
		this.Lev = pro.getProperty("Lev");
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
		log.info(arg0);
	}

	@Override
	public void warn(String arg0) {
		log.warn(arg0);
	}

//	public static void main(String[] args) {
//		LoggerImpl lo = new LoggerImpl();
//		lo.debug("debug");
//		lo.info("info");
//		lo.warn("warn");
//	}
	
}
