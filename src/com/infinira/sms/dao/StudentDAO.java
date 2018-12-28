package com.infinira.sms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.infinira.sms.util.DBService;
import com.infinira.sms.model.Student;
import com.infinira.sms.util.SMSException;
import com.infinira.sms.util.Util;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Date;

public class StudentDAO {
        
    public static int create(Student student){
		if(student == null){
			Util.throwException("SMS_MSG_00045",null);
		}
        Connection conn = DBService.getInstance().getConnection();
        ResultSet rs = null;
        int studentId = 0;
        PreparedStatement pstmt = null;
		int ix = 0;
		
        try{
            pstmt = conn.prepareStatement(INSERT_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setObject(++ix, student.getFirstName());
            pstmt.setObject(++ix, student.getLastName());
            pstmt.setObject(++ix, student.getFatherName());
            pstmt.setObject(++ix, student.getGender());
            pstmt.setObject(++ix, student.getDob());
			
			if (student.getAddress() != null) {
				pstmt.setObject(++ix, student.getAddress());
			} else {
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
			if(student.getNationality() != null){
				pstmt.setObject(++ix, student.getNationality());
			} else{
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
            pstmt.setObject(++ix, student.getStudentPhone());
			
			if(student.getFatherPhone() != null){
				pstmt.setObject(++ix, student.getFatherPhone());
			}else {
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
            pstmt.setObject(++ix, student.getEmail());
            pstmt.setObject(++ix, student.getIdentityType());
            pstmt.setObject(++ix, student.getIdentityNumber());
            pstmt.setObject(++ix, student.getTaxId());
			
			if(student.getStipend() != 0){
            pstmt.setObject(++ix, student.getStipend());
			} else {
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
            pstmt.setObject(++ix, student.getAccountId());
            pstmt.setObject(++ix, student.getAccountName());
            pstmt.setObject(++ix, student.getIfscCode());
            pstmt.setObject(++ix, student.getDoj());
            pstmt.setObject(++ix, student.getDeptId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
               studentId = rs.getInt(1);
			   student.setStudentId(studentId);
            }else{
                Util.throwException("SMS_MSG_00013",null,studentId);
            }  
		} catch(SMSException ex){
            throw ex;
        }catch(Throwable th){
            Util.throwException("SMS_MSG_00013",th,studentId);
        }finally{
            DBService.getInstance().closeResources(rs,pstmt,conn);
        }
        return studentId;
    }
    
    public static int update(Student student){
		if(student == null){
			Util.throwException("SMS_MSG_00045",null);
		}
        Connection conn = DBService.getInstance().getConnection();
        int updatedCount = 0;
        PreparedStatement pstmt = null;
		int ix=0;
        
        try{
            pstmt = conn.prepareStatement(UPDATE_QUERY);
            pstmt.setObject (++ix, student.getFirstName());
            pstmt.setObject (++ix, student.getLastName());
            pstmt.setObject (++ix,student.getFatherName());
            pstmt.setObject (++ix, student.getGender());
            pstmt.setObject (++ix, student.getDob());
			
            if (student.getAddress() != null) {
				pstmt.setObject(++ix, student.getAddress());
			} else {
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
            if(student.getNationality() != null){
				pstmt.setObject(++ix, student.getNationality());
			} else{
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
            pstmt.setString (++ix, student.getStudentPhone());
			
            if(student.getFatherPhone() != null){
				pstmt.setObject(++ix, student.getFatherPhone());
			}else {
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
            pstmt.setObject (++ix, student.getEmail());
            pstmt.setObject (++ix, student.getIdentityType());
            pstmt.setObject (++ix, student.getIdentityNumber());
            pstmt.setObject (++ix, student.getTaxId());
			
            if(student.getStipend() != 0){
            pstmt.setObject(++ix, student.getStipend());
			} else {
				pstmt.setNull(++ix, java.sql.Types.NULL);
			}
			
            pstmt.setObject (++ix, student.getAccountId());
            pstmt.setObject (++ix, student.getAccountName());
            pstmt.setObject (++ix, student.getIfscCode());
            pstmt.setObject (++ix, student.getDoj());
            pstmt.setObject (++ix, student.getDeptId());
            pstmt.setObject (++ix, student.getStudentId());
            updatedCount = pstmt.executeUpdate();
            if(updatedCount == 0){
                Util.throwException("SMS_MSG_00015",null,student.getStudentId());
            }
		} catch(SMSException ex){
            throw ex;
        }catch(Throwable th){
            Util.throwException("SMS_MSG_00015",th,student.getStudentId());
        }finally{
            DBService.getInstance().closeResources(null,pstmt,conn);
        }
        return updatedCount;
    }
    
    public static int delete(int studentId){
		if(studentId == 0){
			Util.throwException("SMS_MSG_00028",null);
		}
        Connection conn = DBService.getInstance().getConnection();
        int studentDeleteStatus = 0;
        PreparedStatement pstmt = null;
        int ix=0;
		
        try{
            pstmt = conn.prepareStatement(DELETE_QUERY);
            pstmt.setInt(++ix, studentId);
            studentDeleteStatus = pstmt.executeUpdate();
            if(studentDeleteStatus == 0){
                Util.throwException("SMS_MSG_00017",null,studentId);
            }
		} catch(SMSException ex){
            throw ex;
        }catch(Throwable th){
            Util.throwException("SMS_MSG_00018",th,studentId);
        }finally{
            DBService.getInstance().closeResources(null,pstmt,conn);
        }
        return studentDeleteStatus;
    }
    
    public static Student getStudent(int studentId) {
		if(studentId == 0){
			Util.throwException("SMS_MSG_00028",null);
		}
        Connection conn = DBService.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
		int ix=0;
		
        try {
            pstmt = conn.prepareStatement(DISPLAY_QUERY);
            pstmt.setInt(++ix,studentId);
            rs = pstmt.executeQuery();
            if (rs != null && rs.next()){
				student= new Student(rs.getString("FIRST_NAME"),rs.getString("LAST_NAME"),rs.getString("FATHER_NAME"),rs.getString("GENDER"),rs.getDate("DOB")); 
				student.setStudentId( rs.getInt("STUDENT_ID") );
                student.setDoj(rs.getDate("DOJ"));
                student.setAddress(rs.getString("ADDRESS"));
                student.setNationality(rs.getString("NATIONALITY"));
                student.setStudentPhone(rs.getString("STUDENT_PHONE"));
                student.setFatherPhone(rs.getString("FATHER_PHONE"));
                student.setEmail(rs.getString("EMAIL"));
                student.setIdentity(rs.getString("IDENTITY_TYPE"),rs.getString("IDENTITY_NUMBER"));
                student.setTaxId(rs.getString("TAX_ID"));
                student.setStipend(rs.getLong("STIPEND"));
                student.setAccountInfo(rs.getString("ACCOUNT_ID"),rs.getString("IFSC_CODE"),rs.getString("ACCOUNT_NAME"));
                student.setDeptId(rs.getInt("DEPARTMENT_ID"));
				student.setCreatedTime(new Date((rs.getTimestamp("CREATED_TIME")).getTime()));
				student.setModifiedTime(new Date((rs.getTimestamp("MODIFIED_TIME")).getTime()));
            }else{
                Util.throwException("SMS_MSG_00019",null,studentId);
            }
		} catch(SMSException ex){
            throw ex;
        } catch (Throwable th) {
            Util.throwException("SMS_MSG_00020",th,studentId);
        }finally{
            DBService.getInstance().closeResources(rs,pstmt,conn);
        }
        return student;
    }
    
    public static List<Student> getAllStudents(){
        Connection conn = DBService.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
		List<Student> stds = new ArrayList<Student>();
        try {
            pstmt = conn.prepareStatement(DISPLAY_ALL_QUERY);
            rs = pstmt.executeQuery();
			while(rs != null && rs.next()){
				student= new Student(rs.getString("FIRST_NAME"),rs.getString("LAST_NAME"),rs.getString("FATHER_NAME"),rs.getString("GENDER"),rs.getDate("DOB")); 
				student.setStudentId( rs.getInt("STUDENT_ID") );
                student.setDoj(rs.getDate("DOJ"));
                student.setAddress(rs.getString("ADDRESS"));
                student.setNationality(rs.getString("NATIONALITY"));
                student.setStudentPhone(rs.getString("STUDENT_PHONE"));
                student.setFatherPhone(rs.getString("FATHER_PHONE"));
                student.setEmail(rs.getString("EMAIL"));
                student.setIdentity(rs.getString("IDENTITY_TYPE"),rs.getString("IDENTITY_NUMBER"));
                student.setTaxId(rs.getString("TAX_ID"));
                student.setStipend(rs.getLong("STIPEND"));
                student.setAccountInfo(rs.getString("ACCOUNT_ID"),rs.getString("IFSC_CODE"),rs.getString("ACCOUNT_NAME"));
                student.setDeptId(rs.getInt("DEPARTMENT_ID"));
				student.setCreatedTime(new Date((rs.getTimestamp("CREATED_TIME")).getTime()));
				student.setModifiedTime(new Date((rs.getTimestamp("MODIFIED_TIME")).getTime()));
				stds.add(student);
            }
		} catch(SMSException ex){
            throw ex;
        } catch (Throwable th) {
            Util.throwException("SMS_MSG_00044",th);
        }finally{
            DBService.getInstance().closeResources(rs,pstmt,conn);
        }
        return stds;
    }
    
    public static String INSERT_QUERY = "INSERT INTO STUDENT(FIRST_NAME,LAST_NAME,FATHER_NAME,GENDER,DOB,ADDRESS,NATIONALITY,STUDENT_PHONE,FATHER_PHONE,EMAIL,IDENTITY_TYPE,IDENTITY_NUMBER,TAX_ID,STIPEND,ACCOUNT_ID,ACCOUNT_NAME,IFSC_CODE,DOJ,DEPARTMENT_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public static String UPDATE_QUERY = "UPDATE STUDENT SET FIRST_NAME =?,LAST_NAME = ?,FATHER_NAME =?,GENDER =?,DOB = ?,ADDRESS =?,NATIONALITY =?,STUDENT_PHONE =?,FATHER_PHONE =?,EMAIL =?,IDENTITY_TYPE =?,IDENTITY_NUMBER=?,TAX_ID =?,STIPEND =?,ACCOUNT_ID =?,ACCOUNT_NAME =?,IFSC_CODE =?,DOJ =?,DEPARTMENT_ID = ? WHERE STUDENT_ID = ?" ;
    
    public static String DELETE_QUERY = "DELETE FROM STUDENT WHERE STUDENT_ID=?";
    
    public static String DISPLAY_QUERY ="SELECT STUDENT_ID,FIRST_NAME,LAST_NAME,FATHER_NAME,GENDER,DOJ,DOB,ADDRESS,NATIONALITY,STUDENT_PHONE,FATHER_PHONE,EMAIL,IDENTITY_NUMBER,IDENTITY_TYPE,TAX_ID,STIPEND,ACCOUNT_ID,ACCOUNT_NAME,IFSC_CODE,DEPARTMENT_ID,CREATED_TIME,MODIFIED_TIME FROM STUDENT WHERE STUDENT_ID =?";
	
	public static String DISPLAY_ALL_QUERY="SELECT * FROM STUDENT";
}

