@echo off
call smsenv.cmd

set SMS_DB_NAME=smsdb
set SMS_DB_USER=smsuser1
set SMS_DB_PWD=smsuser1
set SMS_TABLESPACE_NAME=smstablespace

set SMS_TABLESPACE_DIR=%SMS_HOME%\db\%SMS_TABLESPACE_NAME%
if not exist %SMS_TABLESPACE_DIR% mkdir %SMS_TABLESPACE_DIR%

powershell -ExecutionPolicy Bypass -File .\setupdb.ps1