package com.gec.service;

import java.util.List;

import com.gec.pojo.DeptInf;
import com.github.pagehelper.PageInfo;

public interface DeptService {
	PageInfo<DeptInf> findPageEntity(Integer pageNum,DeptInf dept);

	List<DeptInf> findAll();
	
	DeptInf findById(Integer id);
	
	boolean save(DeptInf entity);
	
	boolean update(DeptInf entity);
	
	boolean delete(Integer id);
}
