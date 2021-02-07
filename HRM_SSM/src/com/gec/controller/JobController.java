package com.gec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gec.pojo.JobInf;
import com.gec.service.JobService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@RequestMapping("/list.action")
	public String list(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
					   JobInf job,Model model){
		model.addAttribute("job",job);
		PageInfo<JobInf> pm = jobService.findPageEntity(pageNum, job);
		model.addAttribute("pageModel", pm);
		return "job/joblist";
	}
	
	@RequestMapping("/add.do")
	public String addView(){
		return "job/jobedit";
	}
	
	@RequestMapping("/update.do")
	public String editView(Integer id,Model model){
		JobInf job = jobService.findById(id);
		model.addAttribute("job",job);
		return "job/jobedit";
	}
	
	@RequestMapping("/addorupdate.action")
	public String addOrUpdate(JobInf job){
		if (job.getId()==null) {
			jobService.save(job);
		} else {
			jobService.update(job);
		}
		return "redirect:/job/list.action";
	}
	
	@RequestMapping("/del.action")
	public String del(Integer[] jobIds){
		for (Integer id : jobIds) {
			jobService.delete(id);
		}
		return "redirect:/job/list.action";
	}
}
