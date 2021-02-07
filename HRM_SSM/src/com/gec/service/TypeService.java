package com.gec.service;

import java.util.List;

import com.gec.pojo.TypeInf;
import com.github.pagehelper.PageInfo;

public interface TypeService {
	PageInfo<TypeInf> findPageEntity(Integer pageNum,TypeInf type);

	List<TypeInf> findAll();
	
	TypeInf findById(Integer id);
	
	boolean save(TypeInf entity);
	
	boolean update(TypeInf entity);
	
	boolean delete(Integer id);
}
