package com.Validation.Controllerr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Validation.Entity.Student;
import com.Validation.Exception.NoDatafound;
import com.Validation.Service.SerInter;
import com.Validation.Service.ServiceInter;

import jakarta.validation.Valid;

@RestController
public class Contro {
	
	@Autowired
	public SerInter inter;
	
	public ServiceInter ss;
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@PostMapping("/postData")
	public ResponseEntity<String>savaMethod(@Valid @RequestBody Student student)
	{
		String sSave = inter.sSave(student);
		
		return new ResponseEntity<>(sSave, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getAll()
	{
		
		List<Student> allDeta = inter.getAllDeta();
		
		return new ResponseEntity<>(allDeta, HttpStatus.OK);	
		
	}
	
	@DeleteMapping("/delete/{integer}")
	public ResponseEntity<String>deleted(@PathVariable Integer integer) throws NoDatafound
	{
		
		String byIdMe = inter.deleteByIdMe(integer);
		
		return new ResponseEntity<String>(byIdMe, HttpStatus.OK);
		
	}

    @PostMapping("/upsave/{id}")
	public ResponseEntity<String>upsa(@Valid @RequestBody Student student,@PathVariable Integer id ) throws NoDatafound
	{
    	
    	String upsave = inter.upsave(student, id);
    	
		return new ResponseEntity<String>(upsave, HttpStatus.OK);
		
	}
	
	
}
