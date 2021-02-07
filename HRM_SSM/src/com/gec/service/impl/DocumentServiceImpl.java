package com.gec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.mapper.DocumentInfMapper;
import com.gec.pojo.DocumentInf;
import com.gec.pojo.DocumentInfExample;
import com.gec.service.DocumentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentInfMapper documentInfMapper;

	@Override
	public PageInfo<DocumentInf> findPageEntity(Integer pageNum, DocumentInf entity) {
		PageHelper.startPage(pageNum, 3);
		DocumentInfExample diE = new DocumentInfExample();
		DocumentInfExample.Criteria cri = diE.createCriteria();
		if (entity.getTitle()!=null && !"".equals(entity.getTitle())) {
			cri.andTitleLike("%"+entity.getTitle()+"%");			
		}
		List<DocumentInf> list = documentInfMapper.selectByExample(diE);
		PageInfo<DocumentInf> info = new PageInfo<DocumentInf>(list);
		return info;
	}

	@Override
	public List<DocumentInf> findAll() {
		return documentInfMapper.selectByExample(new DocumentInfExample());
	}

	@Override
	public DocumentInf findById(Integer id) {
		return documentInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(DocumentInf entity) {
		documentInfMapper.insertSelective(entity);
		return true;
	}

	@Override
	public boolean update(DocumentInf entity) {
		documentInfMapper.updateByPrimaryKeySelective(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		documentInfMapper.deleteByPrimaryKey(id);
		return true;
	}
}
