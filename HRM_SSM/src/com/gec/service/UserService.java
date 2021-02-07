package com.gec.service;

import java.util.List;

import com.gec.pojo.UserInf;
import com.github.pagehelper.PageInfo;

public interface UserService {

	PageInfo<UserInf> findPageEntity(Integer pageNum,UserInf user);
	
	UserInf login(String loginname,String password);
	
	List<UserInf> findAll();
	
	UserInf findById(Integer id);
	
	boolean save(UserInf entity);
	
	boolean update(UserInf entity);
	
	boolean delete(Integer id);

	UserInf findByLoginname(String loginname);
}
