@echo off
call smsenv.cmd

%JAVA_HOME%\bin\java -cp %CLASSPATH% com.infinira.sms.test.SMSTest