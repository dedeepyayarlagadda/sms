package com.infinira.sms.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.MessageFormat;

public class Student{
           
    private int studentId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String gender;
    private Date dob;
    private String address;
    private String nationality;
    private String studentPhone;
    private String fatherPhone;
    private String email;
    private String identityType;
    private String identityNumber;
    private String taxId;
    private long stipend;
    private String accountId;
    private String accountName;
    private String ifscCode;
    private Date doj;
    private int deptId;
    private Date created_time;
    private Date modified_time;
           
    public Student(String firstName,String lastName,String fatherName,String gender,Date dob){
        validate(firstName,"FirstName cannot be null or empty");
        validate(lastName,"LastName cannot be null or empty");
        validate(fatherName,"FatherName cannot be null or empty");
        validate(gender,"Gender cannot be null or empty");
        if(dob == null){
            throw new RuntimeException("DOB cannot be null or empty");
        }
        SMSEnum.Gender.validateGender(gender);
        
        this.firstName=firstName;
        this.lastName=lastName;
        this.fatherName=fatherName;
        this.gender=gender;
        this.dob=dob;
    }
    
    //Setter Methods
    public void setStudentId(int studentId){
        if(studentId == 0){
            throw new RuntimeException("studentId cannot be null or empty");
        }
        this.studentId=studentId;
    }
    
    public void setFirstName(String firstName){
        validate(firstName,"FirstName cannot be null or empty");
        this.firstName=firstName;
    }
    
    public void setLastName(String lastName){
        validate(lastName,"LastName cannot be null or empty");
        this.lastName=lastName;
    }
    
    public void setFatherName(String fatherName) {
        validate(fatherName,"FatherName cannot be null or empty");
        this.fatherName = fatherName;
    }
    
    public void setGender(String gender) {
        validate(gender,"Gender cannot be null or empty");
        SMSEnum.Gender.validateGender(gender);
        this.gender = gender;
    }
    
    public void setDob(Date dob) {
        if(dob == null){
            throw new RuntimeException("DOB cannot be null");
        }
        this.dob = dob;
    }
    
    public void setDoj(Date doj){
        if(doj == null){
            throw new RuntimeException("DOJ cannot be null or empty");
        }
        if(doj.before(dob)){
            throw new RuntimeException(MessageFormat.format("DOB {0} cannot be after DOJ {1}",dob,doj));
        }
        this.doj=doj;
    }
        
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public void setStudentPhone(String Phone) {
        validate(Phone,"StudentPhone cannot be null or empty");
        this.studentPhone = Phone;
    }
    
    public void setFatherPhone(String Phone) {
        this.fatherPhone = Phone;
    }
    
    public void setEmail(String email) {
        validate(email,"Email cannot be null or empty");
        this.email = email;
    }
    
    public void setIdentity(String identityType,String identityNumber){
        validate(identityType," IdentityType cannot be null or empty ");
        validate(identityNumber," IdentityNumber cannot be null or empty ");      
        SMSEnum.IdType.validateIdType(identityType);
        
        if((identityNumber.length() != 12)){
            throw new RuntimeException(MessageFormat.format("Invalid Identity number:{0}, length of identityNumber should be 12 digits",identityNumber));
        }
        this.identityNumber = identityNumber;
        this.identityType = identityType;
    }
        
    public void setTaxId(String taxId) {
        validate(taxId,"taxId cannot be null or empty");
        this.taxId = taxId;
    }
    
    public void setStipend(long stipend) {
        this.stipend = stipend;
    }
        
    public void setAccountInfo(String accountId,String ifscCode,String accountName){
        validate(accountId,"accountId cannot be null or empty");
        validate(ifscCode,"ifscCode cannot be null or empty");
        validate(accountName,"accountName cannot be null or empty");
        
        if(accountId.length() < 6){
            throw new RuntimeException(MessageFormat.format("Invalid accountId : {0} length of accountId should not be less than 5 digits",accountId));
        }
        if(ifscCode.length() != 11){
            throw new RuntimeException(MessageFormat.format("Invalid IFSC Code: {0} should be 11 digits",ifscCode));
        }
        if(accountName.length() < 2){
            throw new RuntimeException(MessageFormat.format("Invalid Account Name: {0}, it should have 3 characters",accountName));
        }

        this.accountId=accountId;
        this.accountName=accountName;
        this.ifscCode=ifscCode;
    }
    
    public void setDeptId(int deptId){
        if(deptId == 0){
            throw new RuntimeException("DepartmentId cannot be Null");
        }
        this.deptId=deptId;
    }
    public void setCreatedTime(Date createdTime){
        this.created_time=createdTime;
    }
    
    public void setModifiedTime(Date modifiedTime){
        this.modified_time=modified_time;
    }
    
    //Getter methods 
    public int getStudentId() {
        return studentId;
    }
   
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFatherName() {
        return fatherName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public Date getDob() {
        return dob;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public String getStudentPhone() {
        return studentPhone;
    }
    
    public String getFatherPhone() {
        return fatherPhone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getIdentityNumber() {
        return identityNumber;
    }
    
    public String getIdentityType() {
        return identityType;
    }
    
    public String getTaxId() {
        return taxId;
    }
    
    public long getStipend() {
        return stipend;
    }
        
    public String getAccountId() {
        return accountId;
    }
    
    public String getAccountName() {
        return accountName;
    }
    
    public String getIfscCode() {
        return ifscCode;
    }
            
    public Date getDoj() {
        return doj;
    }
        
    public int getDeptId(){
        return deptId;
    }
    
    public Date getModifiedTime() {
        return modified_time;
    }
    
    public Date getCreatedTime() {
        return created_time;
    }
    
    public void validate(String value,String mes){
        if(value == null || value.isEmpty()){
            throw new RuntimeException(mes);
        }
    }
          
}
