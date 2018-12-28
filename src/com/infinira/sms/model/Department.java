package com.infinira.sms.model;
   
public class Department{
    
    private int deptId;
    private String deptName;
    
    public Department(String deptName){
        validate(deptName,"DepartmentName cannot be null or empty");
        this.deptName=deptName;
    }
    
    public void setId(int deptId){
		if(deptId == 0){
			throw new RuntimeException("DeptId cannot be null");
		}
        this.deptId=deptId;
    }
    
    public void setName(String deptName){
        validate(deptName,"DepartmentName cannot be null or empty");
        this.deptName=deptName;
    }
    
    public int getId(){
        return deptId;
    }
    
    public String getName(){
        return deptName;
    }
    
    public void validate(String value,String mes){
        if(value == null || value.isEmpty()){
			throw new RuntimeException(mes);
        }
    }   
}