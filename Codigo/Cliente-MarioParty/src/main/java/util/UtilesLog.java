package util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UtilesLog {

	private static Logger log = Logger.getLogger(UtilesLog.class);
	
	@SuppressWarnings("rawtypes")
	public static void registrarInfo(Class clase, TipoLog tipo, String mensaje)
	{
		log = LogManager.getLogger(clase);
		
		switch (tipo) 
		{
			case DEBUG:
				log.debug(mensaje);
				break;
			case ERROR:
				log.error(mensaje);
				break;
			case FATAL:
				log.fatal(mensaje);
				break;
			case INFO:
				log.info(mensaje);
				break;
			case WARNING:
				log.warn(mensaje);
		}
	}

	public static void loggerStackTrace(Exception e, Class<?> className) {
		Logger log = Logger.getLogger(className);
		StringWriter stack = new StringWriter();
		e.printStackTrace(new PrintWriter(stack));
		log.error(stack.toString());
	}
}
