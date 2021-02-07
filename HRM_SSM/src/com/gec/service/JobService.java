package com.gec.service;

import java.util.List;

import com.gec.pojo.JobInf;
import com.github.pagehelper.PageInfo;

public interface JobService {
	PageInfo<JobInf> findPageEntity(Integer pageNum,JobInf job);
	
	JobInf findById(Integer id);
	
	List<JobInf> findAll();
	
	boolean save(JobInf entity);
	
	boolean update(JobInf entity);
	
	boolean delete(Integer id);
}
