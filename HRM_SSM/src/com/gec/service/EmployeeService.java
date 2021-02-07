package com.gec.service;

import java.util.List;

import com.gec.pojo.EmployeeInf;
import com.github.pagehelper.PageInfo;

public interface EmployeeService {
	PageInfo<EmployeeInf> findPageEntity(Integer pageNum,EmployeeInf employee);
	
	List<EmployeeInf> findAll();
	
	EmployeeInf findById(Integer id);
	
	EmployeeInf findByCarId(String carId);
	
	boolean save(EmployeeInf entity);
	
	boolean update(EmployeeInf entity);
	
	boolean delete(Integer id);
}
