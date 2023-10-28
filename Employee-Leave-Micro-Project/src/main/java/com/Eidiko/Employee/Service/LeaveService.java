package com.Eidiko.Employee.Service;

import java.util.List;

import com.Eidiko.Employee.Entity.AccessLevel;
import com.Eidiko.Employee.Entity.AccessRole;
import com.Eidiko.Employee.Entity.EmpLeave;
import com.Eidiko.Employee.Entity.LeaveType;

public interface LeaveService {
	//save Entity
public AccessLevel saveAccessLevel(AccessLevel accessLevel);
public AccessRole saveAccessRole(AccessRole accessRole);
 public EmpLeave saveEmpLeave(EmpLeave empLeave);
public LeaveType saveLeaveType(LeaveType leaveType);


public Object updateLeave(long leaveid);

  // public void leaveDelete(long empId);

public EmpLeave getEmpLeaveDetails(long leaveid);
	//get By Id
public AccessLevel getAccessLevel(Long accessLevelId);
public AccessRole getAccessRole(Long accessRoleId);
public EmpLeave getEmpLeave(Long leaveId);
public LeaveType getLeaveType(Long id);

	//get All
public List<AccessLevel> getAllAccessLevel();
public List<AccessRole> getAllAccessRole();
public List<EmpLeave> getAllEmpLeave();
public List<LeaveType> getAllLeaveType();
}
