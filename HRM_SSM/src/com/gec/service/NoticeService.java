package com.gec.service;

import java.util.List;

import com.gec.pojo.NoticeInf;
import com.github.pagehelper.PageInfo;

public interface NoticeService {
	PageInfo<NoticeInf> findPageEntity(Integer pageNum,NoticeInf notice);
	
	NoticeInf findById(Integer id);
	
	List<NoticeInf> findAll();
	
	boolean save(NoticeInf entity);
	
	boolean update(NoticeInf entity);
	
	boolean delete(Integer id);
}
