package com.esr.ErsRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esr.Entity.EmailHistory;


@Repository
public interface EmailHistoryRepository extends CrudRepository<EmailHistory, Long>
{
	  List<EmailHistory> findByRecipientEmailAndSentDate(String empEmail, 
			  LocalDateTime firstDayOfCurrentMonth);
}
