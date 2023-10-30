package com.Eidiko.Employee.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Eidiko.Employee.LeaveRange.LeaveRange;
@Repository
public interface LeavaeReange extends CrudRepository<LeaveRange, Long> {

}
