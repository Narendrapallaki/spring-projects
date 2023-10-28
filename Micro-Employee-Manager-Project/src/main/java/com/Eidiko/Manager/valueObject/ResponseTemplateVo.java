package com.Eidiko.Manager.valueObject;

import com.Eidiko.Manager.Entity.ManagerEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVo {
	
	
	public ManagerEntity managerEntity;
	
	public Object employee;

}
