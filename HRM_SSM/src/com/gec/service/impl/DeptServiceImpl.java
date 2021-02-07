package com.gec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.mapper.DeptInfMapper;
import com.gec.pojo.DeptInf;
import com.gec.pojo.DeptInfExample;
import com.gec.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptInfMapper deptInfMapper;

	@Override
	public PageInfo<DeptInf> findPageEntity(Integer pageNum, DeptInf entity) {
		PageHelper.startPage(pageNum, 3);
		DeptInfExample diE = new DeptInfExample();
		DeptInfExample.Criteria cri = diE.createCriteria();
		if (entity.getName()!=null && !"".equals(entity.getName())) {
			cri.andNameLike("%"+entity.getName()+"%");			
		}
		List<DeptInf> list = deptInfMapper.selectByExample(diE);
		PageInfo<DeptInf> info = new PageInfo<DeptInf>(list);
		return info;
	}

	@Override
	public List<DeptInf> findAll() {
		return deptInfMapper.selectByExample(new DeptInfExample());
	}

	@Override
	public DeptInf findById(Integer id) {
		return deptInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(DeptInf entity) {
		deptInfMapper.insertSelective(entity);
		return true;
	}

	@Override
	public boolean update(DeptInf entity) {
		deptInfMapper.updateByPrimaryKeySelective(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		deptInfMapper.deleteByPrimaryKey(id);
		return true;
	}
}
