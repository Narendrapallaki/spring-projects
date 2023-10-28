package com.Eidiko.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Eidiko.Employee.Entity.AccessRole;
@Repository
public interface AccessRoleRepository extends JpaRepository<AccessRole, Long>{

}
