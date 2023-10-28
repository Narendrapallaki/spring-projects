package com.Eidiko.Employee.Service;

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

import com.Eidiko.Employee.Entity.AccessLevel;
import com.Eidiko.Employee.Entity.AccessRole;
import com.Eidiko.Employee.Entity.EmpLeave;
import com.Eidiko.Employee.Entity.LeaveType;
import com.Eidiko.Employee.Exception.IdNotFoundException;
import com.Eidiko.Employee.Repository.AccessLevelRepository;
import com.Eidiko.Employee.Repository.AccessRoleRepository;
import com.Eidiko.Employee.Repository.EmpLeaveRepository;
import com.Eidiko.Employee.Repository.LeaveTypeRepository;
import com.Eidiko.Employee.vo.leaveToEmployee;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class LeaveServiceImpl implements LeaveService {

	Logger log = LoggerFactory.getLogger(LeaveServiceImpl.class);
	@Autowired
	private AccessLevelRepository accessLevelRepo;
	@Autowired
	private AccessRoleRepository accessRoleRepo;
	@Autowired
	private EmpLeaveRepository empLeaveRepo;
	@Autowired
	private LeaveTypeRepository leaveTypeRepo;

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
	public AccessLevel saveAccessLevel(AccessLevel accessLevel) {

		return accessLevelRepo.save(accessLevel);

	}

	@Override
	public AccessRole saveAccessRole(AccessRole accessRole) {
		return accessRoleRepo.save(accessRole);
	}

	@Override
	public EmpLeave saveEmpLeave(EmpLeave empLeave) {

		log.info("Inside saveEmpLeave serviceImple");

		EmpLeave save = empLeaveRepo.save(empLeave);

		System.out.println(save.toString());

		try {
			// String s="ht@gmail.com";
			sendMail("narendrapallaki2018@gmail.com", "Leave Letter", save.getEmpId());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("inside save leave");

		return save;
	}

	@Override
	public LeaveType saveLeaveType(LeaveType leaveType) {
		return leaveTypeRepo.save(leaveType);
	}

	// get data by id -------------------
	@Override
	public AccessLevel getAccessLevel(Long accessLevelId) {
		return accessLevelRepo.findById(accessLevelId)
				.orElseThrow(() -> new RuntimeException("AccessLevel is Not Available"));
	}

	@Override
	public AccessRole getAccessRole(Long accessRoleId) {
		return accessRoleRepo.findById(accessRoleId)
				.orElseThrow(() -> new RuntimeException("AccessRole is Not Available"));
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
	public LeaveType getLeaveType(Long id) {
		return leaveTypeRepo.findById(id).orElseThrow(() -> new IdNotFoundException("LeaveType id is Not Available"));
	}

	// get All Entity Data
	@Override
	public List<AccessLevel> getAllAccessLevel() {

		List<AccessLevel> accessLevel = accessLevelRepo.findAll();
		if (accessLevel.isEmpty()) {
			throw new IdNotFoundException("Accesslevel Data not Available");
		} else {
			return accessLevel;
		}
	}

	@Override
	public List<AccessRole> getAllAccessRole() {
		List<AccessRole> accessRole = accessRoleRepo.findAll();
		if (accessRole.isEmpty()) {
			throw new IdNotFoundException("AccessRole Data not Available");
		} else {
			return accessRole;
		}
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

	@Override
	public List<LeaveType> getAllLeaveType() {
		List<LeaveType> leaveType = leaveTypeRepo.findAll();
		if (leaveType.isEmpty()) {
			throw new IdNotFoundException("LeaveType Data not Available");
		} else {
			return leaveType;
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
		//String serviceUrl = "http://EMPLOYEE-SERVICE/emp/getById/" + findByManaid.getEmpid();
		String url="https://EMPLOYEE-SERVICE/emp/get/"+empId;
		String object = restTemplate.getForObject(url, String.class);
	//	System.out.println(object);
		
		
		
		//String[] myArray = { "narendrapallaki@gmail.com", "pallaki.narendra2001@gmail.com" };

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
	public Object updateLeave(long leaveid) {

 EmpLeave empLeave2 = empLeaveRepo.findById(leaveid).orElseThrow(()->new RuntimeException("Id not found in database"));
		//EmpLeave appro = empLeaveRepo.findByEmpId(empid).get(0);
		empLeave2.setStatus("approved");
	EmpLeave save = empLeaveRepo.save(empLeave2);
		return save;
	}
	
	
	public List<EmpLeave> getPendingLeaves(String status) {
		
		List<EmpLeave> ab = empLeaveRepo.findByStatus(status);
		return ab;
		
	}
	
	
	
	
	
	
	
	

}
