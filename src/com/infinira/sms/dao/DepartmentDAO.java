package com.infinira.sms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.infinira.sms.model.Department;
import com.infinira.sms.util.DBService;
import com.infinira.sms.util.SMSException;
import com.infinira.sms.util.Util;
import java.util.List;
import java.util.ArrayList;

public class DepartmentDAO{
    
    public static int create(Department dept){
        if(dept == null){
            Util.throwException("SMS_MSG_00001", null);
        }
        Connection conn = DBService.getInstance().getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;     
        int deptId = 0;
		int ix = 0;
		
        try{
            pstmt = conn.prepareStatement(INSERT_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString (++ix, dept.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
               deptId = rs.getInt(1);
               dept.setId(deptId);
            } else{
                Util.throwException("SMS_MSG_00002",null,dept.getName());
                
            }
        }catch(SMSException ex){
            throw ex;
        }catch(Throwable th){
            Util.throwException("SMS_MSG_00003",th,dept.getName());
        }finally{
            DBService.getInstance().closeResources(rs,pstmt,conn);
        }
        return deptId;
    }
    
    public static int update(Department dept){
        if(dept == null){
            Util.throwException("SMS_MSG_00004",null);
        }
        Connection conn = DBService.getInstance().getConnection();
        int updateStatus = 0;
        PreparedStatement pstmt = null;
		int ix = 0;
		
        try{
            pstmt = conn.prepareStatement(UPDATE_QUERY);
            pstmt.setString (++ix, dept.getName());
            pstmt.setInt(++ix,dept.getId());
            updateStatus = pstmt.executeUpdate();
            if(updateStatus == 0){
                Util.throwException("SMS_MSG_00005",null,dept.getId());
            }
        } catch(SMSException ex){
            throw ex;
        } catch(Throwable th){
            Util.throwException("SMS_MSG_00006",th,dept.getId());
        } finally{
            DBService.getInstance().closeResources(null,pstmt,conn);
        }
        return updateStatus;
    }

    public static int delete(int deptId){
        if(deptId == 0){
            Util.throwException("SMS_MSG_00007",null);
        }
        Connection conn = DBService.getInstance().getConnection();
        int deleteStatus = 0;
        PreparedStatement pstmt = null;
		int ix = 0;
		
        try{
            pstmt = conn.prepareStatement(DELETE_QUERY);
            pstmt.setInt(++ix,deptId);
            deleteStatus = pstmt.executeUpdate();
            if(deleteStatus == 0){
                Util.throwException("SMS_MSG_00008",null,deptId);
            }
        }catch(SMSException ex){
            throw ex;
        }catch(Throwable th){
            Util.throwException("SMS_MSG_00009",th,deptId);
        }finally{
            DBService.getInstance().closeResources(null,pstmt,conn);
        }
        return deleteStatus;
    }

    public static Department getDept(int deptId){
        if(deptId == 0){
            Util.throwException("SMS_MSG_00010",null);
        }
        Connection conn = DBService.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        Department dept = null;
		int ix = 0;
		
        try{
            pstmt = conn.prepareStatement(DISPLAY_QUERY);
            pstmt.setInt(++ix,deptId);
            rs = pstmt.executeQuery();
            if (rs != null && rs.next()){
                dept=new Department(rs.getString("DEPARTMENT_NAME"));
                dept.setId(rs.getInt("DEPARTMENT_ID"));
            }else{
                Util.throwException("SMS_MSG_00011",null,deptId);
            }
        }catch(SMSException ex){
            throw ex;
        }catch(Throwable th){
            Util.throwException("SMS_MSG_00012",th,deptId);
        }finally{
            DBService.getInstance().closeResources(rs,pstmt,conn);
        }
        return dept;
    }
    
    public static List<Department> getAllDepartments(){
		Connection conn = DBService.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        Department dept = null;
		List<Department> depts = new ArrayList<Department>();
		
        try{
            pstmt = conn.prepareStatement(DISPLAY_ALL_QUERY);
            rs = pstmt.executeQuery();
			while(rs != null && rs.next()){
				dept=new Department(rs.getString("DEPARTMENT_NAME"));
				dept.setId(rs.getInt("DEPARTMENT_ID"));
				depts.add(dept);
            }
        }catch(SMSException ex){
            throw ex;
        }catch(Throwable th){
            Util.throwException("SMS_MSG_00043",th);
        }finally{
            DBService.getInstance().closeResources(rs,pstmt,conn);
        }
        return depts;
    }
  
    private static final String INSERT_QUERY = " INSERT INTO DEPARTMENT(DEPARTMENT_NAME) VALUES (?)";
    private static final String UPDATE_QUERY = " UPDATE DEPARTMENT SET DEPARTMENT_NAME = ? WHERE DEPARTMENT_ID = ?";
    private static final String DELETE_QUERY = " DELETE FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";
    private static final String DISPLAY_QUERY = "SELECT DEPARTMENT_ID , DEPARTMENT_NAME FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";
    private static final String DISPLAY_ALL_QUERY = "SELECT * FROM DEPARTMENT";
}

