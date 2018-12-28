-----------------------------------------creating database objects-----------------------------------------
create user :sms_db_user with password :'sms_db_pwd';
alter role :sms_db_user with SUPERUSER CREATEDB CREATEROLE INHERIT LOGIN;
create tablespace :sms_tablespace_name location :'sms_tablespace_dir';
create database :sms_db_name;