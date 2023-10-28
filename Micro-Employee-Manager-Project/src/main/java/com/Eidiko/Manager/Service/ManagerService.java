package com.Eidiko.Manager.Service;

import com.Eidiko.Manager.Entity.ManagerEntity;
import com.Eidiko.Manager.valueObject.ResponseTemplateVo;

public interface ManagerService {

	public ManagerEntity saveManagerEntity(ManagerEntity managerEntity);
	
	public ManagerEntity getManageById(Long id);

	public ResponseTemplateVo getManagerWithEmployeeId(Long id);
	
	
}
