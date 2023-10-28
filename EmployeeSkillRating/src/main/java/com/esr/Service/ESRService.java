package com.esr.Service;

import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.esr.EmployeeSkillRatingApplication;
import com.esr.CustomException.NoWorkFound;
import com.esr.Entity.EmailHistory;
import com.esr.Entity.EmpSkill;
import com.esr.Entity.EmployeeData;
import com.esr.ErsRepo.EmailHistoryRepository;
import com.esr.ErsRepo.EmpRatingRepo;
import com.esr.ErsRepo.EmpRepo;
import com.esr.ErsRepo.EmpSkilRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ESRService {

	@Autowired
	public EmpRepo empRepo;
	@Autowired
	public EmpSkilRepo empSkilRepo;
	@Autowired
	public EmpRatingRepo ratingRepo;

	public Logger logger = LoggerFactory.getLogger(EmployeeSkillRatingApplication.class);

	public EmpSkill getBySkillId(Integer empSkilId) {
		EmpSkill empSkill = empSkilRepo.findById(empSkilId).get();
		logger.info("data get" + empSkill);
		return empSkill;
	}

	public List<EmpSkill> getEmployeesWithWorkingSkills(String work) {
		try {
			List<EmpSkill> findByWork = empSkilRepo.findByWork(work);

			return findByWork;
		} catch (Exception e) {
			logger.error("An error occurred while fetching employees with working skills: {}", e.getMessage());

			throw e;
		}

	}

//	
//	public List<Object[]>findWorkTechCommRatingsForTraineesWithLowRatings()
//	{
//		return empRepo.findWorkTechCommRatingsForTraineesWithLowRatings();
//		
//	}

//	public List<EmployeeData>findWorkTechCommRatingsForTraineesWithLowRatings()
//	{
//		List<String>list=new ArrayList<>();
//		
//		List<EmployeeData> withLowRatings = empRepo.findEmployeesWithLowRatings();
//		
//	
//		return withLowRatings;
//		
//	}

	@Autowired
	public EmailHistoryRepository emailHistoryRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine engine;

	// @Scheduled(cron = "1 * * 1-10 4 0-7")
	// @Scheduled(fixedRate = 10000)
	public void sendMail() {
		// System.out.println("ooo");
		/*
		 * try { List<EmployeeData> withLowRatings =
		 * empRepo.findEmployeesWithLowRatings(); System.out.println(withLowRatings);
		 * for (EmployeeData employeeData : withLowRatings) {
		 * 
		 * MimeMessage message = javaMailSender.createMimeMessage(); MimeMessageHelper
		 * helper = new MimeMessageHelper(message, true);
		 * 
		 * Context context = new Context();
		 * 
		 * 
		 * context.setVariable("username", employeeData.getName());
		 * context.setVariable("techRating",
		 * employeeData.getEmpRating().getTechRating());
		 * context.setVariable("commRating",employeeData.getEmpRating().getCommRating())
		 * ; String process = engine.process("email-template.html", context);
		 * 
		 * 
		 * helper.setTo(employeeData.getEmail()); helper.setSubject("Monthly reviews");
		 * helper.setText(process, true); javaMailSender.send(message); } } catch
		 * (MessagingException e) { e.printStackTrace(); }
		 */

	

		try {
			LocalDate today = LocalDate.now();

			// if (emailHistory == null ||
			// ChronoUnit.DAYS.between(emailHistory.getLastEmailSentDate(), today) >= 1) {

			List<EmployeeData> withLowRatings = empRepo.findEmployeesWithLowRatings();
			//System.out.println(withLowRatings);
			for (EmployeeData employeeData : withLowRatings) {

				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				Context context = new Context();
				context.setVariable("username", employeeData.getName());
				context.setVariable("techRating", employeeData.getEmpRating().getTechRating());
				context.setVariable("commRating", employeeData.getEmpRating().getCommRating());
				String process = engine.process("email-template.html", context);
				
				 //EmailHistory previousEmails = emailHistoryRepository.findByEmpMailAndSentDateAfterOrSentDateEquals(employeeData.getEmail(), firstDayOfCurrentMonth, firstDayOfCurrentMonth);		
				List<EmailHistory> previousEmails = emailHistoryRepository
					       .findByRecipientEmailAndSentDate(employeeData.getEmail(), LocalDateTime.now());
//		          for (EmailHistory emailHistory : previousEmails) {
//					System.out.println(emailHistory);
//				}
				EmailHistory emailHistory = new EmailHistory();
				
				LocalDateTime sentDate = emailHistory.getSentDate();
				if (sentDate == null || !sentDate.toLocalDate().isEqual(today) || previousEmails.isEmpty()) {
					
					

					helper.setTo(employeeData.getEmail());
					helper.setSubject("Monthly reviews");
					helper.setText(process, true);
					javaMailSender.send(message);
					
					emailHistory.setRecipientEmail(employeeData.getEmail());
					emailHistory.setSentDate(LocalDateTime.now());
					emailHistory.setLastEmailSentDate(today);
					
					// Send the email
					//emailHistoryRepository.save(emailHistory);
					logger.info("send mail details " + emailHistoryRepository.save(emailHistory));
				}
				
			}
		
			
			// }
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
