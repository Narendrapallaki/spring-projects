package com.esr.ErsRepo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esr.Entity.EmpRating;

@Repository
public interface EmpRatingRepo extends CrudRepository<EmpRating, Integer> {

	 List<EmpRating> findByTechRatingAndCommRating(double techRating, double commRating);
}
