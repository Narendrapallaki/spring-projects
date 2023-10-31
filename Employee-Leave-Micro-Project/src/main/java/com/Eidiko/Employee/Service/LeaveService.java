package com.Eidiko.Employee.Service;

import java.util.List;


import com.Eidiko.Employee.Entity.EmpLeave;

import jakarta.mail.MessagingException;


public interface LeaveService {

 public String saveEmpLeave(EmpLeave empLeave);



public Object updateLeave(Long leaveid,String status) throws MessagingException;

	

public EmpLeave getEmpLeave(Long leaveId);



public List<EmpLeave> getAllEmpLeave();

}
