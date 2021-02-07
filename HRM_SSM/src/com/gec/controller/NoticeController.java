package com.gec.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gec.pojo.NoticeInf;
import com.gec.pojo.TypeInf;
import com.gec.service.NoticeService;
import com.gec.service.TypeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private TypeService typeService;
	
	@RequestMapping("/list.action")
	public String list(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
					   NoticeInf notice,Model model){
		model.addAttribute("notice",notice);
		PageInfo<NoticeInf> pm = noticeService.findPageEntity(pageNum, notice);
		model.addAttribute("pageModel", pm);
		return "notice/noticelist";
	}
	
	@RequestMapping("/add.do")
	public String addView(Model model){
		List<TypeInf> types = typeService.findAll();
		model.addAttribute("types", types);
		return "notice/notice_save_update";
	}
	
	@RequestMapping("/update.do")
	public String editView(Integer id,Model model){
		NoticeInf notice = noticeService.findById(id);
		model.addAttribute("notice",notice);
		List<TypeInf> types = typeService.findAll();
		model.addAttribute("types", types);
		return "notice/notice_save_update";
	}
	
	@RequestMapping("/saveorupdate.action")
	public String addOrUpdate(@RequestParam("text")String content,Integer type_id,Integer userId,NoticeInf notice){
		notice.setContent(content);
		notice.setModifyDate(new Date());
		notice.setTypeId(type_id);
		notice.setUserId(userId);
		if (notice.getId()==null) {
			notice.setCreateDate(new Date());
			noticeService.save(notice);
		} else {
			noticeService.update(notice);
		}
		return "redirect:/notice/list.action";
	}
	
	@RequestMapping("/del.action")
	public String del(Integer[] noticeIds){
		for (Integer id : noticeIds) {
			noticeService.delete(id);
		}
		return "redirect:/notice/list.action";
	}
	
	@RequestMapping("/listType.action")
	public String listType(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
					   TypeInf type,Model model){
		model.addAttribute("type",type);
		PageInfo<TypeInf> info = typeService.findPageEntity(pageNum, type);
		model.addAttribute("pageModel", info);
		return "notice/typelist";
	}
	
	@RequestMapping("/addType.do")
	public String addTypeView(){
		return "notice/type_save_update";
	}
	
	@RequestMapping("/editType.do")
	public String editTypeView(Integer id,Model model){
		TypeInf type = typeService.findById(id);
		model.addAttribute("type",type);
		return "notice/type_save_update";
	}
	
	@RequestMapping("/addorupdateType.action")
	public String addOrUpdateType(TypeInf type,Integer userId){
		type.setUserId(userId);
		type.setModifyDate(new Date());
		if (type.getId()==null) {
			typeService.save(type);
		} else {
			typeService.update(type);
		}
		return "redirect:/notice/listType.action";
	}
	
	@RequestMapping("/delType.action")
	public String delType(Integer[] typeIds){
		for (Integer id : typeIds) {
			typeService.delete(id);
		}
		return "redirect:/notice/listType.action";
	}
}
