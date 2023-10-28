package com.Eidiko.Manager.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Eidiko.Manager.CustomException.ResourceNotFoundException;
import com.Eidiko.Manager.Entity.ManagerEntity;
import com.Eidiko.Manager.Repository.ManagerRepository;
import com.Eidiko.Manager.valueObject.Employee;
import com.Eidiko.Manager.valueObject.ResponseTemplateVo;

import lombok.extern.slf4j.Slf4j;

@Service
public class ManagerServiceImp implements ManagerService {

	private Logger log = LoggerFactory.getLogger(ManagerServiceImp.class);

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	public RestTemplate restTemplate;

	@Override
	public ManagerEntity saveManagerEntity(ManagerEntity managerEntity) {

		ManagerEntity save = managerRepository.save(managerEntity);
		log.info("ManagerEntity going to save : " + save);

		return save;
	}

	@Override
	public ManagerEntity getManageById(Long id) {

		ManagerEntity elseThrow = managerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("This Id Not Available in db !!"));
		log.info("fetching the data by id: " + elseThrow);

		return elseThrow;
	}

	@Override
	public ResponseTemplateVo getManagerWithEmployeeId(Long manaid) {

		log.info("getManagerWithEmployeeId");
		ResponseTemplateVo rtv = new ResponseTemplateVo();
		ManagerEntity findByManaid = managerRepository.findByManaid(manaid);
		
		String serviceUrl = "http://EMPLOYEE-SERVICE/emp/getById/" + findByManaid.getEmpid();
		Object employee = restTemplate.getForObject(serviceUrl, Object.class);


//		Object employee = restTemplate.getForObject("http://localhost:8081/emp/getById/" + findByManaid.getEmpid(),
//				Object.class);
                                            //http://localhost:8081/emp/getById/1
		rtv.setManagerEntity(findByManaid);
		rtv.setEmployee(employee);
		return rtv;
	}

}
