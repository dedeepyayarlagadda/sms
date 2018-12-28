@echo off

call smsenv.cmd
set PG_HOME=%SMS_HOME%\..\db\bigsql\bigsql\pg11
if %PG_HOME%=="" goto INVALIDSMSHOME
if not exist %PG_HOME% goto INVALIDSMSHOME

set DATA_FOLDER=%PG_HOME%\..\data\pg11
if %DATA_FOLDER%=="" goto INVALIDSMSHOME
if not exist %DATA_FOLDER% goto INVALIDSMSHOME

set SMS_DB_NAME=smsdb
if %SMS_DB_NAME%=="" goto INVALIDSMSHOME

set SMS_DB_USER=smsuser1
if %SMS_DB_USER%=="" goto INVALIDSMSHOME

set SMS_DB_PWD=smsuser1
if %SMS_DB_PWD%=="" goto INVALIDSMSHOME

set SMS_TABLESPACE_NAME=istablespace
if %SMS_TABLESPACE_NAME%=="" goto INVALIDSMSHOME

set SMS_TABLESPACE_DIR=%PG_HOME%\..\%SMS_TABLESPACE_NAME%
if %SMS_TABLESPACE_DIR%=="" goto INVALIDSMSHOME
if not exist %SMS_TABLESPACE_DIR% goto INVALIDSMSHOME

rem Creating the table space path location
if not exist %SMS_TABLESPACE_DIR% mkdir %SMS_TABLESPACE_DIR%

rem Creating the data folder
if not exist %DATA_FOLDER% mkdir %DATA_FOLDER%

rem powershell -ExecutionPolicy Bypass -File .\setupdb.ps1
powershell -ExecutionPolicy Bypass ". .\setupdb.ps1; main"

goto:EOF

:INVALIDSMSHOME
    echo ERROR: SMS_HOME location not provided or does not exist.
