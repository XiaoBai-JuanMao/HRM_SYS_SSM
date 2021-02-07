package com.gec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gec.pojo.DeptInf;
import com.gec.service.DeptService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/list.action")
	public String list(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
							 DeptInf dept,Model model){
		model.addAttribute("dept",dept);
		PageInfo<DeptInf> info = deptService.findPageEntity(pageNum, dept);
		model.addAttribute("pageModel", info);
		
		return "dept/deptlist";
	}
	
	@RequestMapping("/add.do")
	public String addView(){
		return "dept/deptedit";
	}
	
	@RequestMapping("/update.do")
	public String updateView(Integer id,Model model){
		DeptInf dept = deptService.findById(id);
		model.addAttribute("dept",dept);
		return "dept/deptedit";
	}
	
	@RequestMapping("/saveorupdate.action")
	public String saveOrUpdate(DeptInf dept){
		if (dept.getId()==null) {
			deptService.save(dept);
		}else {
			deptService.update(dept);
		}
		return "redirect:/dept/list.action";
	}
	
	@RequestMapping("/del.action")
	public String del(Integer[] deptIds){
		for (Integer id : deptIds) {
			deptService.delete(id);
		}
		return "redirect:/dept/list.action";
	}
}
