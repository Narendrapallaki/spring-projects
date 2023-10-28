package com.Validation.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Validation.Entity.Student;
import com.Validation.Exception.NoDatafound;
import com.Validation.Userdetails.UserData;

@Service
public interface SerInter {
	
	
    public String sSave(Student student);
     
    
    public List<Student>getAllDeta();
    
    public String deleteByIdMe(Integer integer);
    
 //   public Student updateById(Integer integer);
    
    
    public String upsave(Student us,Integer id) throws NoDatafound;
    
    
}
