@echo off
call smsenv.cmd

powershell -ExecutionPolicy Bypass .\stoppostgres.ps1