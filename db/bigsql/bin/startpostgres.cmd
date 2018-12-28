@echo off
set PG_HOME=%cd%\..\bigsql\pg11
set DATA_FOLDER=%PG_HOME%\..\data\pg11

powershell -ExecutionPolicy Bypass ". .\setupdb.ps1; start-server"

pause