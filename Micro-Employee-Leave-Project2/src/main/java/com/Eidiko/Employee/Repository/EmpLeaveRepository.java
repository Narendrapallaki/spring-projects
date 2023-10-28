package com.Eidiko.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Eidiko.Employee.Entity.EmpLeave;

@Repository
public interface EmpLeaveRepository extends JpaRepository<EmpLeave, Long> {

	void deleteByLeaveid(long leaveid);
	EmpLeave findByLeaveid(long leaveid);
//	public EmpLeave updateByEmpId(long empId);
	List<EmpLeave> findByEmpId(long empId);
	
	
	public List<EmpLeave> findByStatus(String status);
}
