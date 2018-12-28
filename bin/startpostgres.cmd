@echo off
call smsenv.cmd

powershell -ExecutionPolicy Bypass .\startpostgres.ps1