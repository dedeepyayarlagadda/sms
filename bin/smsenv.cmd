@echo off

set SMS_HOME=c:\sms
if "%SMS_HOME%"=="" goto INVALIDSMSHOME
if not exist %SMS_HOME% goto INVALIDSMSHOME

set PATH=%SMS_HOME%\bin;%path%
set CLASSPATH=%classpath%;%SMS_HOME%\lib\*;%SMS_HOME%\db\dbscripts;%SMS_HOME%\config;

set PG_HOME=%SMS_HOME%\db\bigsql\pg11
if not exist %PG_HOME% goto INVALIDPGHOME

set PGDATA=%SMS_HOME%\db\data\pg11
if not exist %PGDATA% mkdir %PGDATA%

goto :EOF

:INVALIDSMSHOME
    echo ERROR: SMS_HOME is empty or does not exist.
	goto :EOF
	
:INVALIDPGHOME
	echo ERROR: PG_HOME does not exist.