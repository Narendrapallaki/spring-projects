package com.Eidiko.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Eidiko.Employee.Entity.LeaveType;
@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long>{

}
