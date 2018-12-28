package com.infinira.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.InputStream;
import java.util.Properties;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.Scanner;
import java.sql.DatabaseMetaData;

public class DBService{
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String PROPERTY_FILE = "dbconfig.properties";
    private String userName = null;
    private String password = null;
    private String url = null;
	private static DBService dbservice;
	
	public static DBService getInstance(){
		if(dbservice == null){
			synchronized(DBService.class){
				if(dbservice == null){
					dbservice = new DBService();
				}
			}
		}
		return dbservice;
	}
    
    public DBService(){
		init();
    }
    
    private void init(){
        Properties prop = new Properties();
        InputStream inputStream = null;
        try{
           inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE);
        } catch(Throwable th){
           throw new RuntimeException(MessageFormat.format("property file <{0}> not found",PROPERTY_FILE),th);
        }
        try{
            prop.load(inputStream);
        } catch(Throwable th){
            throw new RuntimeException(MessageFormat.format("Cannot load data from file:<{0}>",PROPERTY_FILE),th);
        }
        url = prop.getProperty(URL);
        validate(url,URL,PROPERTY_FILE);
        userName = prop.getProperty(USERNAME);
        validate(userName,USERNAME,PROPERTY_FILE);
        password = prop.getProperty(PASSWORD);
        validate(password,PASSWORD,PROPERTY_FILE);
    }
	
	private void validate(String value,String property,String file){
        if(value == null || value.isEmpty()){
            throw new RuntimeException(MessageFormat.format("Invalid property <{0}> in property file <{1}>",property,file));
        }
    }
	
	public Connection getConnection(){
        Connection conn=null;
        try{
            conn = DriverManager.getConnection(url,userName,password);
        } catch(Throwable th){
            throw new RuntimeException(MessageFormat.format("Connection failed with Url: <{0}> and Username:<{1}>",url,userName),th);
        }
        return conn;
    }
	
    public  void closeResources(ResultSet rs, Statement stmt, Connection conn){
        if (rs!=null){
            try{
                rs.close();
            } catch(Throwable th){
                //throw new RuntimeException("failed to close ResultSet",th);
            }
        }
        if (stmt != null){
            try{
                stmt.close();
            } catch(Throwable th){
				 //throw new RuntimeException("failed to close Statement",th);  
            }
        }
        if (conn != null){
            try{
                conn.close();
            } catch(Throwable th){
				//throw new RuntimeException("failed to close Connection",th); 
            }
        }
    }
	
	private List<String> getSchema(String schemaFile) {
		if(schemaFile == null || schemaFile.isEmpty()){
			throw new RuntimeException("Schema file  cannot be null or empty");
		}
		List<String> queryList = null;	
		InputStream inputStream = null;
		Scanner scanner = null; 
		
		try{
			queryList = new ArrayList<>();
			inputStream = DBService.class.getClassLoader().getResourceAsStream(schemaFile);
			scanner = new Scanner(inputStream).useDelimiter(";;");
			String queryLine = null;
			while (scanner.hasNext()) {
				queryLine=scanner.next();
				queryList.add(queryLine);
			}
		} catch(Throwable th){
			throw new RuntimeException(MessageFormat.format("Failed to fetch data from <{0}>",schemaFile),th);
		} finally {
			try{
				inputStream.close();
			}catch(Throwable th){
				//throw new RuntimeException("Failed to close buffered reader",th);
			}
			try{
				scanner.close();
			}catch(Throwable th){
				//throw new RuntimeException("Failed to close scanner ",th);
			}				
		}
		if(queryList.size() == 0 || queryList.contains(null)){
				throw new RuntimeException(MessageFormat.format("Queries cannot be null or empty for <{0}>",schemaFile));
		}
		return queryList;
	}
	
	public void prepareSchema(String schemaFileName, String tableNameToVerify){
		List<String> queryList = getSchema(schemaFileName);
		prepareSchema(queryList,tableNameToVerify);
	}
	
	public void prepareSchema(List<String> queryList, String tableNameToVerify) {
		if(queryList.size() == 0 || queryList.contains(null)){
			throw new RuntimeException("Queries  cannot be null or empty ");
		}
		Connection con=null;
		Statement stmt = null;
		String query= null;
		
		try {
			con= getConnection();
			if(tableNameToVerify != null && !tableNameToVerify.isEmpty()){
				if(!isTableExists(tableNameToVerify, con)){
					stmt = con.createStatement();
					for (String tempquery : queryList) {
						query=tempquery;
						stmt.execute(tempquery);
					}
				}
			}
		} catch(Throwable th) {
			throw new RuntimeException(MessageFormat.format("Failed to execute query: <{0}> ",query),th);
		} finally {
			closeResources(null,stmt,con);	
		}
	}

	public boolean isTableExists(String tableName, Connection conn){
		if(tableName == null || tableName.isEmpty()){
			throw new RuntimeException("Table Name cannot be null or empty");
		}
		if(conn == null){
			throw new RuntimeException("Connection cannot be null");
		}
		boolean tableExist = false;
		ResultSet rs= null;
		DatabaseMetaData dbm=null;

		try {
			dbm = conn.getMetaData();
			rs = dbm.getTables(null, null,tableName.toLowerCase(), null);
			if(rs == null){
				rs = dbm.getTables(null,null,tableName.toUpperCase(),null);
			}
			
			if (rs != null && rs.next()) {
				tableExist = true;
			}

		} catch (Throwable th) {
			throw new RuntimeException(MessageFormat.format("Failed to get Meta Deta for table <{0}>",tableName),th);
		} finally{
			closeResources(rs,null,null);
		}
		return tableExist;
	}
}



