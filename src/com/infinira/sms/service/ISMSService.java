package com.infinira.sms.service;

import com.infinira.sms.model.Student;
import com.infinira.sms.model.Department;
import java.util.List;

public interface ISMSService  {
    
    public  int createStudent(Student student);
    public  int updateStudent(Student student);
    public  int deleteStudent(int studentId);
    public Student getStudent(int studentId);
	public List<Student> getAllStudents();
	
    public  int createDept(Department dept);
    public  int updateDept(Department dept);
    public  int deleteDept(int deptId);
    public Department getDept(int deptId);
	public List<Department> getAllDepartments();
}