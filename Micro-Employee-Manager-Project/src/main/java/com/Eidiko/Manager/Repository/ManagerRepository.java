package com.Eidiko.Manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Eidiko.Manager.Entity.ManagerEntity;
@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long>{
	
	
	
	public ManagerEntity findByManaid(long manaid);

}
