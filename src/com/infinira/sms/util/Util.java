package com.infinira.sms.util;

import org.apache.log4j.Logger;
import com.infinira.sms.util.ResourceUtil;
import com.infinira.sms.util.SMSException;
import com.infinira.sms.util.DBService;
import java.text.MessageFormat;

public class Util{
	private static final String SCHEMA_FILE = "smscreateschema.sql";
	private static final String TABLE_NAME = "DEPARTMENT";
	public static Logger logger;
	private static String message;
	
	static {
		try{
			logger = Logger.getLogger(Util.class.getName());
		} catch(Throwable th){
			message = "Error While importing log properties file.";
		}
		DBService.getInstance().prepareSchema(SCHEMA_FILE,TABLE_NAME);
	}
	
	public static SMSException throwException(String msgId, Throwable th, Object...params){
		if(params.length < 0){
			message = "Null parameters are not allowed";
		}
		String msg = ResourceUtil.getInstance().getSMSMessage(msgId);
		String formattedMsg = MessageFormat.format(msg, params);
		logger.error(formattedMsg, th);
		return new SMSException(formattedMsg, th);
	}
}


