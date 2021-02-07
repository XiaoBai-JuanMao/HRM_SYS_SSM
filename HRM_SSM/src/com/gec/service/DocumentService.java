package com.gec.service;

import java.util.List;

import com.gec.pojo.DocumentInf;
import com.github.pagehelper.PageInfo;

public interface DocumentService {
	PageInfo<DocumentInf> findPageEntity(Integer pageNum,DocumentInf document);

	List<DocumentInf> findAll();
	
	DocumentInf findById(Integer id);
	
	boolean save(DocumentInf entity);
	
	boolean update(DocumentInf entity);
	
	boolean delete(Integer id);
}
