package com.identity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.identity.dto.AccessLevel;
@Repository
public interface AccessLevelRepo extends CrudRepository<AccessLevel, Long>{

}
