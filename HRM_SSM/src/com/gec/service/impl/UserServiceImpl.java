package com.gec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.mapper.UserInfMapper;
import com.gec.pojo.UserInf;
import com.gec.pojo.UserInfExample;
import com.gec.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserInfMapper userInfMapper;
	
	@Override
	public PageInfo<UserInf> findPageEntity(Integer pageNum, UserInf entity) {
		PageHelper.startPage(pageNum, 3);
		UserInfExample uiE = new UserInfExample();
		UserInfExample.Criteria cri = uiE.createCriteria();
		if(entity.getLoginname()!=null && !entity.getLoginname().equals("")) {
			cri.andLoginnameLike("%"+entity.getLoginname()+"%");			
		}
		if(entity.getUsername()!=null && !entity.getUsername().equals("")) {
			cri.andUsernameLike("%"+entity.getUsername()+"%");	
		}
		if(entity.getStatus()!=null && entity.getStatus()>0) {
			cri.andStatusEqualTo(entity.getStatus());
		}
		List<UserInf> list = userInfMapper.selectByExample(uiE);
		PageInfo<UserInf> info = new PageInfo<UserInf>(list);
		return info;
	}

	@Override
	public List<UserInf> findAll() {
		return userInfMapper.selectByExample(new UserInfExample());
	}

	@Override
	public UserInf login(String loginname, String password) {
		UserInfExample uiE = new UserInfExample();
		UserInfExample.Criteria cri = uiE.createCriteria();
		cri.andLoginnameEqualTo(loginname);
		cri.andPasswordEqualTo(password);
		List<UserInf> list = userInfMapper.selectByExample(uiE);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public UserInf findById(Integer id) {
		return userInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserInf findByLoginname(String loginname) {
		UserInfExample uiE = new UserInfExample();
		UserInfExample.Criteria cri = uiE.createCriteria();
		cri.andLoginnameEqualTo(loginname);
		List<UserInf> list = userInfMapper.selectByExample(uiE);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public boolean save(UserInf entity) {
		userInfMapper.insertSelective(entity);
		return true;
	}

	@Override
	public boolean update(UserInf entity) {
		userInfMapper.updateByPrimaryKeySelective(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		userInfMapper.deleteByPrimaryKey(id);
		return true;
	}
}
