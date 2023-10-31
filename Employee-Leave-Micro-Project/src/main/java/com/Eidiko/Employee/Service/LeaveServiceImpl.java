package com.Eidiko.Employee.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.Eidiko.Employee.Entity.EmpLeave;

import com.Eidiko.Employee.Exception.IdNotFoundException;

import com.Eidiko.Employee.LeaveRange.LeaveRange;

import com.Eidiko.Employee.Repository.EmpLeaveRepository;
import com.Eidiko.Employee.Repository.LeavaeReange;

import com.Eidiko.Employee.vo.leaveToEmployee;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class LeaveServiceImpl implements LeaveService {

	Logger log = LoggerFactory.getLogger(LeaveServiceImpl.class);
	@Autowired
	private EmpLeaveRepository empLeaveRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine engine;
	@Autowired
	private RestTemplate restTemplate;

	public leaveToEmployee leaveTypeEmp(long leaveid) {
		leaveToEmployee lte = new leaveToEmployee();
		EmpLeave empLeave = empLeaveRepo.findById(leaveid).get();

		String url = "http://EMPLOYEE-SERVICE/emp/get/" + empLeave.getEmpId();

		// Object forObject = restTemplate.getForObject(url, Object.class);
		String mail = restTemplate.getForObject(url, String.class);
		System.out.println(mail);
		lte.setEmpLeave(empLeave);
		// lte.setEvo(forObject);
		return lte;

	}

	@Override
	public EmpLeave getEmpLeaveDetails(long leaveid) {

		EmpLeave leave = empLeaveRepo.findByLeaveid(leaveid);
		return null;

	}

	// save Entity -----------

	
	@Override
	public String saveEmpLeave(EmpLeave empLeave) {

		log.info("Inside saveEmpLeave serviceImple");		
		LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(26);
		LocalDate endDate = LocalDate.now().withDayOfMonth(31);

	//	LocalDate now = LocalDate.parse("2023-10-25");
//		LocalDate now = LocalDate.now();
//		System.out.println(now);
		
		LocalDate now = LocalDate.parse("2023-10-26");
		
		empLeave.setCreateDate(now);
		       //   String s="2023-10-23";
	//	LocalDateTime ldt=LocalDateTime.parse("2023-10-25");
		if (now.isAfter(endDate)) {
           
			//System.out.println("false");
			throw new RuntimeException("Monthly leave experied");
			// throw new MonthlyLeaveExpiredException("Monthly leave expired");
		} else {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

			LocalDateTime cutoffTime = LocalDateTime.of(now, LocalTime.of(15, 40));
			String formattedCutoffTime = cutoffTime.format(formatter);

//			LocalDateTime now1 = LocalDateTime.now();
//			String formattedNow = now1.format(formatter);
			
			  LocalDateTime dateTime = LocalDateTime.parse("2023-10-26T11:45", formatter);

			  String format = dateTime.format(formatter);
			
	        System.out.println(format+"vs"+formattedCutoffTime);
	        if (format.compareTo(formattedCutoffTime) < 0) {
			    System.out.println("Leave application accepted");
			    
			   // leavaeReange.save(empLeave);
			   EmpLeave save = empLeaveRepo.save(empLeave);
			   try {
					// String s="ht@gmail.com";
					sendMail("narendrapallaki2018@gmail.com", "Leave Letter", save.getEmpId());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    return "Leave application accepted";
			    
			} else {
			    System.out.println("Leave not accepted after 4 PM on the 25th");
			    return "Leave not accepted after 4pm on the 25th";
			}

			}	

		
		
		
		
		
		

		

		//System.out.println(save.toString());

//		try {
//			// String s="ht@gmail.com";
//			sendMail("narendrapallaki2018@gmail.com", "Leave Letter", save.getEmpId());
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	//	System.out.println("inside save leave");

		//return save;
	}

	

//get empleave details based on leaveid
	@Override
	public EmpLeave getEmpLeave(Long leaveId) {
		EmpLeave orElseThrow = empLeaveRepo.findByLeaveid(leaveId);
		// .orElseThrow(() -> new RuntimeException("EmpLeave Data is Not Available"));
		log.info("inside getEmpLeave ");
		return orElseThrow;
	}

	

	@Override
	public List<EmpLeave> getAllEmpLeave() {
		List<EmpLeave> empLeave = empLeaveRepo.findAll();

		if (empLeave.isEmpty()) {
			throw new IdNotFoundException("EmpLeave Data not Available");
		} else {

			return empLeave;
		}
	}

	

	public void leaveDelete(long leaveid) {
		empLeaveRepo.deleteById(leaveid);

	}

	public void sendMail(String tomail, String subject, long empId) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		EmpLeave findById = empLeaveRepo.findByEmpId(empId).get(empLeaveRepo.findByEmpId(empId).size() - 1);
		Context cc = new Context();
		// cc.setVariable("empid", findById.getEmpId());
		cc.setVariable("leaveType", findById.getLeaveType());
		cc.setVariable("startDate", findById.getStartDate());
		cc.setVariable("endDate", findById.getEndDate());
		cc.setVariable("reason", findById.getLeaveReason());
		String process = engine.process("leave-template.html", cc);
		helper.setTo(tomail);
		// String serviceUrl = "http://EMPLOYEE-SERVICE/emp/getById/" +
		// findByManaid.getEmpid();
		String url = "https://EMPLOYEE-SERVICE/emp/get/" + empId;
		String object = restTemplate.getForObject(url, String.class);
		// System.out.println(object);

		// String[] myArray = { "narendrapallaki@gmail.com",
		// "pallaki.narendra2001@gmail.com" };
    
		InternetAddress[] ccAddresses = new InternetAddress[2];

		ccAddresses[0] = new InternetAddress(object);
		ccAddresses[1] = new InternetAddress("pallaki.narendra2001@gmail.com");
//		for (int i = 0; i < myArray.length; i++) {
//			ccAddresses[i] = new InternetAddress(myArray[i]);
//		}
		helper.setCc(ccAddresses);
		helper.setSubject(subject);
		helper.setText(process, true);
		javaMailSender.send(message);
		log.info("mail sended");
	}

	@Override
	public Object updateLeave(Long leaveid,String status) throws MessagingException {

		EmpLeave empLeave2 = empLeaveRepo.findById(leaveid)
				.orElseThrow(() -> new RuntimeException("Id not found in database"));
		empLeave2.setStatus(status);
		
		String url = "https://EMPLOYEE-SERVICE/emp/get/" + empLeave2.getEmpId();
		String object = restTemplate.getForObject(url, String.class);
		
		System.out.println("Employee mail :"+object);
		

	       MimeMessage createMimeMessage = javaMailSender.createMimeMessage();
	       MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(createMimeMessage, true);
	         
	  //     mimeMessageHelper.setTo("pallaki.narendra2001@gmail.com");
	       mimeMessageHelper.setTo(object);
	       mimeMessageHelper.setSubject("Leave status");    
	       Context cc = new Context();
	       cc.setVariable("status", status);
	       String process = engine.process("Aproved-leave.html", cc);
	       mimeMessageHelper.setText(process, true);
	       javaMailSender.send(createMimeMessage);	  
		
		EmpLeave save = empLeaveRepo.save(empLeave2);
		return save;
	}
	

	public List<EmpLeave> getPendingLeaves(String status) {

		List<EmpLeave> ab = empLeaveRepo.findByStatus(status);
		return ab;
    
	}
	//--------------------------------------
/*	@Autowired
	private LeavaeReange leavaeReange;
	
	
	public String saveLeaveRange(LeaveRange lr)
	{
		
		LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(26);
		LocalDate endDate = LocalDate.now().withDayOfMonth(31);

		
		LocalDate now = LocalDate.parse("2023-10-25");
		//LocalDate now = LocalDate.now();
		System.out.println(now);
		
		lr.setCreateDate(now);
		       //   String s="2023-10-23";
	//	LocalDateTime ldt=LocalDateTime.parse("2023-10-25");
		if (now.isAfter(endDate)) {
           
			//System.out.println("false");
			throw new RuntimeException("Monthly leave experied");
			// throw new MonthlyLeaveExpiredException("Monthly leave expired");
		} 
		else {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

			LocalDateTime cutoffTime = LocalDateTime.of(now, LocalTime.of(15, 0));
			String formattedCutoffTime = cutoffTime.format(formatter);

			LocalDateTime now1 = LocalDateTime.now();
			String formattedNow = now1.format(formatter);
	        System.out.println(formattedNow+"vs"+formattedCutoffTime);
	        if (formattedNow.compareTo(formattedCutoffTime) < 0) {
			    System.out.println("Leave application accepted");
			    
			    leavaeReange.save(lr);
			    return "Leave application accepted";
			    
			} 
	        else 
	        {
			    System.out.println("Leave not accepted after 4 PM on the 25th");
			    return "Leave not accepted after 4pm on the 25th";
			}

		}	
	}*/
}
