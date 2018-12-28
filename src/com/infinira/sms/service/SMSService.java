package com.infinira.sms.service;

import com.infinira.sms.dao.DepartmentDAO;
import com.infinira.sms.model.Department;
import com.infinira.sms.model.Student;
import com.infinira.sms.dao.StudentDAO;
import java.util.List;

public class SMSService implements ISMSService{
    
    public  int createStudent(Student student){
        return StudentDAO.create(student);
    }
        
    public  int updateStudent(Student student){
        return StudentDAO.update(student);
    }
    
    public  int deleteStudent(int studentId){
        return StudentDAO.delete(studentId);
    } 
    
    public Student getStudent(int studentId){
        return StudentDAO.getStudent(studentId);
    }
    
    public List<Student> getAllStudents(){
        return StudentDAO.getAllStudents();
    }
    
    public  int createDept(Department dept){
        return DepartmentDAO.create(dept);
    }
    
    public  int updateDept(Department dept){
        return DepartmentDAO.update(dept);
    }
    
    public  int deleteDept(int deptID){
        return DepartmentDAO.delete(deptID);
    }

    public Department getDept(int deptId){
        return DepartmentDAO.getDept(deptId);
    }
    
    public List<Department> getAllDepartments(){
        return DepartmentDAO.getAllDepartments();
    } 
}