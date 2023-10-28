package com.Eidiko.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Eidiko.Employee.Entity.AccessLevel;
@Repository
public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long>{

}
