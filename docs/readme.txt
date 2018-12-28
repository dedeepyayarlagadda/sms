Introduction:
--------------
Student Management System is a project built using Java language with PostgreSQL database. It is used to store, administer and manage all aspects of student information like student details, enrollment details and their department information. The system allows easy modification of students and other details.
-----------------------------
REQUIREMENTS
------------

JDK 1.6, URL: https://www.oracle.com/technetwork/java/javase/downloads/index.html

CONTENTS
--------

This sms zip contains:
	
	bin        - Executable files are provided.
	config     - Configuration files required for the project SMS.
			     User can do the required configuration changes in the files under this folder.
	db         - Contains postgres distribution files and SMS schema file.
	lib        - Contains the dependent jar files to run the SMS project.
	logs       - Contains log files.
    readme.txt - that explains details regarding Student Management System.
    

RUNNING SMS IF POSTGRESQL IS INSTALLED IN THE RUNNING SYSTEM:
------------------------------------------------------------
1. Unzip the files in the sms.zip file on you hard drive recommended to be C:\SMS.
2. Set SMS_HOME as the directory to which sms.zip is extracted e.g C:\SMS  
3. Run SMS_HOME\bin\smsenv.cmd to set environment variables
4. Set PostgreSQL db url,username and password in SMS_HOME\config\dbconfig.properties. 
5. Run SMS_HOME\bin\smstest.cmd to execute the SMS project.


RUNNING SMS IF POSTGRESQL IS NOT INSTALLED IN THE RUNNING SYSTEM:
----------------------------------------------------------------

1. Unzip the files in the sms.zip file on you hard drive recommended to be C:\SMS.  
2. Set SMS_HOME as the directory to which sms.zip is extracted e.g C:\SMS 
3. Run SMS_HOME\bin\smsenv.cmd to set environment variables.
4. Run SMS_HOME\bin\setupdb.cmd to install database and create required database,user and tables.
4. Run SMS_HOME\bin\smstest.cmd to execute the SMS project.




	