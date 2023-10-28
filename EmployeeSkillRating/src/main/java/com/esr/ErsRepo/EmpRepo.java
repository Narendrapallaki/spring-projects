package com.esr.ErsRepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esr.Entity.EmployeeData;

@Repository
public interface EmpRepo extends CrudRepository<EmployeeData, Integer> {
	
	
	
//	   @Query("SELECT skill.work, rating.techRating, rating.commRating " +
//	           "FROM EmployeeData e " +
//	           "JOIN e.empSkill skill " +
//	           "JOIN e.empRating rating " +
//	           "WHERE skill.work = 'trainee' " +
//	           "AND rating.techRating < 2.5 " +
//	           "AND rating.commRating < 2.5")
	
//	@Query("SELECT e.email " +
//		       "FROM EmployeeData e " +
//		       "JOIN e.empSkill skill " +
//		       "JOIN e.empRating rating " +
//		       "WHERE skill.work = 'trainee' " +
//		       "AND rating.techRating < 2.5 " +
//		       "OR rating.commRating < 2.5")
//	    List<Object[]> findWorkTechCommRatingsForTraineesWithLowRatings();

	
	@Query("SELECT e " +
		       "FROM EmployeeData e " +
		       "JOIN e.empSkill skill " +
		       "JOIN e.empRating rating " +
		       "WHERE skill.work = 'trinee' " +
		       "AND (rating.techRating < 2.5 AND rating.commRating < 2.5)")
		List<EmployeeData> findEmployeesWithLowRatings();
}
