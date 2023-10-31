package com.Eidiko.Employee.Service;

import java.util.List;


import com.Eidiko.Employee.Entity.EmpLeave;


public interface LeaveService {

 public String saveEmpLeave(EmpLeave empLeave);



public Object updateLeave(long leaveid);

  // public void leaveDelete(long empId);

public EmpLeave getEmpLeaveDetails(long leaveid);
	//get By Id

public EmpLeave getEmpLeave(Long leaveId);



public List<EmpLeave> getAllEmpLeave();

}
