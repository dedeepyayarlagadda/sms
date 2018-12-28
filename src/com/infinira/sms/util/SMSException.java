package com.infinira.sms.util;

import java.text.MessageFormat;

public class SMSException extends RuntimeException{

	public SMSException(String msg, Throwable th){
	    super(msg,th);
	}
}