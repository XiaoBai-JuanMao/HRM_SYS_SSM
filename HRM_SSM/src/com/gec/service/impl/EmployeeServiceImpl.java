package com.gec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.mapper.EmployeeInfMapper;
import com.gec.pojo.EmployeeInf;
import com.gec.pojo.EmployeeInfExample;
import com.gec.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeInfMapper employeeInfMapper;

	@Override
	public PageInfo<EmployeeInf> findPageEntity(Integer pageNum, EmployeeInf entity) {
		PageHelper.startPage(pageNum, 3);
		EmployeeInfExample eiE = new EmployeeInfExample();
		EmployeeInfExample.Criteria cri = eiE.createCriteria();
		if (entity.getJobId()!=null && entity.getJobId()>0) {
			cri.andJobIdEqualTo(entity.getJobId());
		}
		if (entity.getName()!=null && !"".equals(entity.getName())) {
			cri.andNameLike("%"+entity.getName()+"%");
		}
		if (entity.getCardId()!=null && !"".equals(entity.getCardId())) {
			cri.andJobIdEqualTo(entity.getJobId());
		}
		if (entity.getSex()!=null && entity.getSex()>0) {
			cri.andSexEqualTo(entity.getSex());
		}
		if (entity.getPhone()!=null && !"".equals(entity.getPhone())) {
			cri.andPhoneLike("%"+entity.getPhone()+"%");
		}
		if (entity.getDeptId()!=null && entity.getDeptId()>0) {
			cri.andDeptIdEqualTo(entity.getDeptId());
		}
		List<EmployeeInf> list = employeeInfMapper.selectByExample(eiE);
		PageInfo<EmployeeInf> info = new PageInfo<EmployeeInf>(list);
		return info;
	}

	@Override
	public List<EmployeeInf> findAll() {
		return employeeInfMapper.selectByExample(new EmployeeInfExample());
	}

	@Override
	public EmployeeInf findById(Integer id) {
		return employeeInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public EmployeeInf findByCarId(String carId) {
		EmployeeInfExample eiE = new EmployeeInfExample();
		EmployeeInfExample.Criteria cri = eiE.createCriteria();
		cri.andCardIdEqualTo(carId);
		List<EmployeeInf> list = employeeInfMapper.selectByExample(eiE);
		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;			
		}
	}
	
	@Override
	public boolean save(EmployeeInf entity) {
		employeeInfMapper.insertSelective(entity);
		return true;
	}

	@Override
	public boolean update(EmployeeInf entity) {
		employeeInfMapper.updateByPrimaryKeySelective(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		employeeInfMapper.deleteByPrimaryKey(id);
		return true;
	}
}
