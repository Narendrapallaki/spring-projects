package com.Validation.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Validation.Entity.Student;
import com.Validation.Exception.NoDatafound;
import com.Validation.Repositery.Repo;
import com.Validation.Userdetails.UserData;



@Service
public class ServiceInter implements SerInter {

	@Autowired
	public Repo repo;

	@Override
	public String sSave(Student student) {
		// TODO Auto-generated method stub
		repo.save(student);
		return "saved data"+student.getId();
	}

	@Override
	public List<Student> getAllDeta() {
		// TODO Auto-generated method stub
		return (List<Student>) repo.findAll();
	}

	@Override
	public String deleteByIdMe(Integer integer) {
		// TODO Auto-generated method stub
		
		repo.deleteById(integer);
		
		
		return "data Deleted";
		
	}

//	@Override
//	public Student updateById(Integer id) {
//		// TODO Auto-generated method stub
//		Student student = repo.findById(id).get();
//		return student;
//	}

	@Override
	public String upsave(Student us,Integer id) throws NoDatafound {
		// TODO Auto-generated method stub
		
		Student student = repo.findById(id).orElseThrow(  ()-> new NoDatafound("not update")
				);
		//student.setId(us.getId());
		student.setEmail(us.getEmail());
		student.setMobile(us.getMobile());
		student.setName(us.getName());
		student.setPassword(us.getPassword());
		
		repo.save(student);
		return "user data saved";
	}


     
	
	

	

	

	

	
	


}
