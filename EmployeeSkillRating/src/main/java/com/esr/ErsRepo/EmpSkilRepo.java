package com.esr.ErsRepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esr.Entity.EmpSkill;
@Repository
public interface EmpSkilRepo extends CrudRepository<EmpSkill, Integer> {

	  List<EmpSkill> findByWork(String work);
//	  
//	  @Query("SELECT s FROM EmpSkill s JOIN EmpRating r ON s.employeeData = r.employeeData " +
//	           "WHERE s.work = :work AND r.techRating = :techRating AND r.commRating = :commRating")
//	    List<EmpSkill> findByWorkAndTechAndCommRatings(
//	            @Param("work") String work,
//	            @Param("techRating") double techRating,
//	            @Param("commRating") double commRating
//	    );
}
