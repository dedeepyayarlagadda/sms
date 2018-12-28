Introduction:
--------------
Student Management System is a project built using Java and PostgreSQL database. It is used to save and manage student and their department information. The system allows easy modification of students and other details.
-----------------------------
REQUIREMENTS
------------

JDK 1.8, URL: https://www.oracle.com/technetwork/java/javase/downloads/index.html

CONTENTS
--------

This sms zip contains:
	
	bin        - Executable files are provided.
	config     - Configuration files required for the project SMS.
		     User can do the required configuration changes in the files under this folder.
	db         - Contains Embedded postgres and SMS schema file.
	lib        - Contains the dependent jar files to run the SMS project.
	logs       - Contains log files.
    readme.txt     - Explains details regarding Student Management System.
    

Setup
-----

1. Create app directory E.g: C:\SMS and extract sms.zip in C:\SMS.
2. Set SMS_HOME as the directory where sms.zip is extracted in SMS_HOME\bin\smsenv.cmd file. e.g C:\SMS 
3. Run SMS_HOME\bin\setupdb.cmd to install database and create required database,user and tables. 

NOTE: Use Step 2 & Step 3 for the first time setup only.

Run time:
--------
Go to SMS_HOME\bin
1. Run startsms.cmd to start SMS execution.
3. Run smstest.cmd to execute SMS project.
4. Run stopsms.cmd to stop execution of sms.




	
