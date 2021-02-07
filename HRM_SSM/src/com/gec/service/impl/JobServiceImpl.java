package com.gec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.mapper.JobInfMapper;
import com.gec.pojo.JobInf;
import com.gec.pojo.JobInfExample;
import com.gec.service.JobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("jobService")
public class JobServiceImpl implements JobService {
	@Autowired
	private JobInfMapper jobInfMapper;

	@Override
	public PageInfo<JobInf> findPageEntity(Integer pageNum, JobInf entity) {
		PageHelper.startPage(pageNum, 3);
		JobInfExample jiE = new JobInfExample();
		JobInfExample.Criteria cri = jiE.createCriteria();
		if (entity.getName()!=null && !"".equals(entity.getName())) {
			cri.andNameLike("%"+entity.getName()+"%");			
		}
		List<JobInf> list = jobInfMapper.selectByExample(jiE);
		PageInfo<JobInf> info = new PageInfo<JobInf>(list);
		return info;
	}

	@Override
	public List<JobInf> findAll() {
		return jobInfMapper.selectByExample(new JobInfExample());
	}

	@Override
	public JobInf findById(Integer id) {
		return jobInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(JobInf entity) {
		jobInfMapper.insertSelective(entity);
		return true;
	}

	@Override
	public boolean update(JobInf entity) {
		jobInfMapper.updateByPrimaryKeySelective(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		jobInfMapper.deleteByPrimaryKey(id);
		return true;
	}
}
