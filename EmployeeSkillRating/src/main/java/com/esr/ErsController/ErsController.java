package com.esr.ErsController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esr.Entity.EmpSkill;
import com.esr.Entity.EmployeeData;
import com.esr.ErsRepo.EmpRepo;
import com.esr.Service.ESRService;

import jakarta.mail.MessagingException;

@RestController
public class ErsController {

	@Autowired
	public ESRService esrService;

	@GetMapping("/getById/{empSkillId}")
	public ResponseEntity<Map<String, Object>> getByIdSkillTable(@PathVariable Integer empSkillId) {

		EmpSkill bySkillId = esrService.getBySkillId(empSkillId);
		Map<String, Object> map = new HashMap<>();
		map.put("message", "your data");
		map.put("result", bySkillId);
		map.put("status", HttpStatus.CREATED.value());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@GetMapping("/getByWork/{work}")
	public ResponseEntity<Map<String, Object>> getByWork(@PathVariable String work) {
		List<EmpSkill> employeesWithWorkingSkills = esrService.getEmployeesWithWorkingSkills(work);

		Map<String, Object> map = new HashMap<>();
		if (!employeesWithWorkingSkills.isEmpty()) {
			map.put("message", "Your data");
			map.put("result", employeesWithWorkingSkills);
			map.put("status", HttpStatus.OK.value());

			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("message", "Invalid work find in database");
			map.put("result", null); // You may omit this line, as 'result' can be omitted.
			map.put("status", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	}

//	@GetMapping("/getWTC")
//	public ResponseEntity<Map<String,Object>>getWorkTechRatingCommRating()
//	{
//		
//		List<String> withLowRatings = esrService.findWorkTechCommRatingsForTraineesWithLowRatings();	
//		Map<String,Object>map=new HashMap<>();
//		map.put("message", "your data");	
//		map.put("result", withLowRatings);
//		map.put("status", HttpStatus.CREATED.value());
//		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);	
//	}

	@Autowired
	public EmpRepo empRepo;

	//@PostMapping("/send")
	public ResponseEntity<Map<String, Object>> sendEmail() 
	//(@RequestParam("subject") String subject)
	{
		Map<String, Object> map = new HashMap<>();
		try {
			esrService.sendMail();
			map.put("result", " Emails sent successfully.");
			map.put("status", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			map.put("Erroe", "Some thing wrong");
			map.put("Status", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_GATEWAY);
		}
	}
	
	
	//@Scheduled(cron = "1 * * 1-10 4 0-7")
	 @Scheduled(fixedRate = 20000)
	public void sendMailAuto()
	{
		sendEmail();
	}

}
