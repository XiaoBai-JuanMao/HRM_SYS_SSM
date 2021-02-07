package com.gec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.mapper.TypeInfMapper;
import com.gec.pojo.TypeInf;
import com.gec.pojo.TypeInfExample;
import com.gec.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("typeService")
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeInfMapper typeInfMapper;

	@Override
	public PageInfo<TypeInf> findPageEntity(Integer pageNum, TypeInf entity) {
		PageHelper.startPage(pageNum, 3);
		TypeInfExample tiE = new TypeInfExample();
		TypeInfExample.Criteria cri = tiE.createCriteria();
		if (entity.getName()!=null && !"".equals(entity.getName())) {
			cri.andNameLike("%"+entity.getName()+"%");			
		}
		List<TypeInf> list = typeInfMapper.selectByExample(tiE);
		PageInfo<TypeInf> info = new PageInfo<TypeInf>(list);
		return info;
	}

	@Override
	public List<TypeInf> findAll() {
		return typeInfMapper.selectByExample(new TypeInfExample());
	}

	@Override
	public TypeInf findById(Integer id) {
		return typeInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(TypeInf entity) {
		typeInfMapper.insertSelective(entity);
		return true;
	}

	@Override
	public boolean update(TypeInf entity) {
		typeInfMapper.updateByPrimaryKeySelective(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		typeInfMapper.deleteByPrimaryKey(id);
		return true;
	}
}
