package com.gec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gec.mapper.NoticeInfMapper;
import com.gec.pojo.NoticeInf;
import com.gec.pojo.NoticeInfExample;
import com.gec.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeInfMapper noticeInfMapper;

	@Override
	public PageInfo<NoticeInf> findPageEntity(Integer pageNum, NoticeInf entity) {
		PageHelper.startPage(pageNum, 3);
		NoticeInfExample niE = new NoticeInfExample();
		NoticeInfExample.Criteria cri = niE.createCriteria();
		if (entity.getName()!=null && !"".equals(entity.getName())) {
			cri.andNameLike("%"+entity.getName()+"%");			
		}
		List<NoticeInf> list = noticeInfMapper.selectByExample(niE);
		PageInfo<NoticeInf> info = new PageInfo<NoticeInf>(list);
		return info;
	}

	@Override
	public List<NoticeInf> findAll() {
		return noticeInfMapper.selectByExample(new NoticeInfExample());
	}

	@Override
	public NoticeInf findById(Integer id) {
		return noticeInfMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(NoticeInf entity) {
		noticeInfMapper.insertSelective(entity);
		return true;
	}

	@Override
	public boolean update(NoticeInf entity) {
		noticeInfMapper.updateByPrimaryKeySelective(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		noticeInfMapper.deleteByPrimaryKey(id);
		return true;
	}
}
