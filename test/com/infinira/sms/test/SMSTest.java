package com.infinira.sms.test;

import com.infinira.sms.service.SMSService;
import java.sql.Date;
import java.util.Calendar;
import com.infinira.sms.model.Department;
import com.infinira.sms.model.Student;
import com.infinira.sms.util.Util;

public class SMSTest{

	Util util = new Util();
    SMSService smsService = new SMSService();
	
    public int createDept(String deptName) {
    	Department dept = new Department(deptName); 
    	return smsService.createDept(dept);
    }
    
    public int updateDept(String deptName,int deptId) {
    	Department dept = new Department(deptName);
		dept.setId(deptId);
    	return smsService.updateDept(dept);
    }
    
    public int createStudent(int deptId,String firstName) {
    	Calendar cal = Calendar.getInstance();
        cal.set(1994,1,8);      
        Date sqldate = new Date((cal.getTime()).getTime());
    	Student student = new Student(firstName, "Y", "LNRAO", "f",sqldate);
    	student.setEmail("dedeepya99@gmail.com");
        student.setAddress("Bapatla");
        student.setDeptId(deptId);
        student.setFatherPhone("9491706570");
        student.setStudentPhone("7981878383");
        student.setIdentity("AADHAR","345314659560");
        student.setAccountInfo("30495787","ANDB0000034","Ria");
        student.setNationality("Indian");
        student.setTaxId("AKXYY76010");
        student.setStipend(15000);
        student.setDoj(sqldate);
        student.setDob(sqldate);
    	return smsService.createStudent(student);
    }
    
    public int updateStudent(int studentId,int deptId,String firstName) {
    	Calendar cal = Calendar.getInstance();
    	cal.set(1994, 1, 8);
    	Date sqldate = new Date((cal.getTime()).getTime());
    	Student student = new Student(firstName, "yarl", "skkk", "f",sqldate);
        student.setStudentId(studentId);    
        student.setEmail("dedeepya99@gmail.com");
        student.setAddress("bapatla");
        student.setDeptId(deptId);
        student.setFatherPhone("9491706570");
        student.setStudentPhone("7981878383");
        student.setIdentity("AADHAR","346510659918");
        student.setAccountInfo("30040710","ANDB0000034","Rias");
        student.setNationality("Indian");
        student.setTaxId("AKXPZ76093");
        student.setStipend(15000);
        student.setDoj(sqldate);
        student.setDob(sqldate);
    	return smsService.updateStudent(student);
    }
    
    public Department getDepartment(int deptId) {
    	return smsService.getDept(deptId);
    }
    
    public Student getStudent(int studentId) {
    	return smsService.getStudent(studentId);
    }
    
	public int deleteDept(int deptId){
		return smsService.deleteDept(deptId);
	}
	
	public int deleteStudent(int studentId){
		return smsService.deleteStudent(studentId);
	}

    public static void main(String[] args){
		
    	SMSTest smstest = new SMSTest();
    	//Create Department
		String deptName = "Agbsc";
    	int deptId =  smstest.createDept(deptName);
        System.out.println("Department "+deptName+" created with DeptID : " +deptId);
		
        
        //Update department 
		String update_dept="Agro";
        int updateStatus = smstest.updateDept(update_dept,deptId);
        if(updateStatus == 1){
            System.out.println("Department Id: " +deptId+" is successfully updated");
        }else{
            System.out.println("Failed to update Department: "+update_dept);
        }
        
        //Create Student 
		String firsName = "Deepya";
        int studentId=smstest.createStudent(deptId,firsName);
        if(studentId == 0){
            System.out.println("Failed to create Student");
        }else{
            System.out.println("Student: "+firsName+ "created with ID : " +studentId);
        }
        
        //Update Student
		String firstName = "Dedeepya";
        int studentUpdateStatus = smstest.updateStudent(studentId,deptId,firstName);
        if(studentUpdateStatus == 1){
            System.out.println("StudentId: "+studentId+" is successfully updated as: "+firsName);
        }else{
            System.out.println("Updated of StudentId"+studentId+"  failed");
        }
        
        //Display department
        Department displayDept = smstest.getDepartment(deptId);
        if(displayDept == null){
            System.out.println("Cannot display department values"); 
        }else{
            System.out.println("Department: "+displayDept.getId()+ " has Department Name: "+displayDept.getName());
        }
        
        //Display Student
        Student displayStudent=smstest.getStudent(studentId);
        if(displayStudent == null){
            System.out.println("Cannot display student values"); 
        }else{
            System.out.println("Student "+displayStudent.getStudentId()+ " values are FirstName: " +displayStudent.getFirstName()+ " Last Name:  " +displayStudent.getLastName()+ " Father Name: " +displayStudent.getFatherName()+ " Gender: " +displayStudent.getGender()+ " Email:  " +displayStudent.getEmail()+ "Address: "+displayStudent.getAddress()+" DepartmentId: " +displayStudent.getDeptId()+ " DOB: "+displayStudent.getDob()+" Identity Type: "+displayStudent.getIdentityType()+ " Identity Number: "+displayStudent.getIdentityNumber()+" Father phone: "+displayStudent.getFatherPhone()+"Created Time: "+displayStudent.getCreatedTime());
        }
		
		//Delete Student
		smstest.deleteStudent(studentId);
		System.out.println("StudentId: " +studentId+ " deleted successfully");
		
		//Delete Department
		smstest.deleteDept(deptId);
		System.out.println("DepartmentId: " +deptId+ " deleted successfully");
		
		
    }
}