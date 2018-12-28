package com.infinira.sms.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.text.MessageFormat;

public class ResourceUtil{
	
	private static final String SMS_MSG_FILE = "SMSMessage";
	private static ResourceBundle rsbundle;
	private static volatile ResourceUtil resourceUtil;
  
    public static ResourceUtil getInstance() {
		if (resourceUtil == null) {
			synchronized(ResourceUtil.class) {
				if (resourceUtil == null) {
					resourceUtil = new ResourceUtil();                 
				}
			}
		}
		return resourceUtil;
	}
    
	private ResourceUtil() { 
        init();
	} 
    private void init() {
        try {
            rsbundle = ResourceBundle.getBundle(SMS_MSG_FILE, Locale.getDefault());
        }catch (Throwable th) {
             throw new RuntimeException(MessageFormat.format("File <{0}>.properties not found in Class path.", SMS_MSG_FILE), th);
        }
    }
	
	public String getSMSMessage(String msgId){
		if(msgId == null){
			msgId = "MsgId is empty";
		}
		String msg = "";
		try {
			msg = rsbundle.getString(msgId);
		} catch (Throwable th){
			msg = msgId + "is invalid";
		}
		return msg;
	}
}