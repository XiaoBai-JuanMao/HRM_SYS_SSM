package com.gec.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gec.pojo.DeptInf;
import com.gec.pojo.EmployeeInf;
import com.gec.pojo.JobInf;
import com.gec.service.DeptService;
import com.gec.service.EmployeeService;
import com.gec.service.JobService;
import com.gec.util.MySimpleDateFormat;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private JobService jobService;
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/list.action")
	public String list(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			   		   @RequestParam(value="job_id",defaultValue="0")Integer jobId,
			   		   @RequestParam(value="dept_id",defaultValue="0")Integer deptId,
			   		   EmployeeInf employee,Model model){
		employee.setJobId(jobId);
		employee.setDeptId(deptId);
		model.addAttribute("employee",employee);
		PageInfo<EmployeeInf> info = employeeService.findPageEntity(pageNum, employee);
		model.addAttribute("pageModel",info);
		List<JobInf> jobList = jobService.findAll();
		model.addAttribute("jobList", jobList);
		List<DeptInf> deptList = deptService.findAll();
		model.addAttribute("deptList", deptList);
		return "employee/employeelist";
	}
	
	@RequestMapping("/add.do")
	public String addView(Model model){
		List<JobInf> jobList = jobService.findAll();
		model.addAttribute("jobs", jobList);
		List<DeptInf> deptList = deptService.findAll();
		model.addAttribute("depts", deptList);
		return "employee/employeeedit";
	}
	
	@RequestMapping("/carIdAjax.action")
	@ResponseBody
	public EmployeeInf carIdAjax(String cardId,HttpServletResponse response) throws IOException{
		EmployeeInf employee = employeeService.findByCarId(cardId);
		/*
		 * PrintWriter out = response.getWriter(); ObjectMapper mapper = new
		 * ObjectMapper(); String json = mapper.writeValueAsString(employee);
		 * out.print(json); out.close();
		 */
		return employee;
	}
	
	@RequestMapping("/update.do")
	public String updateView(Integer id,Model model){
		EmployeeInf employee = employeeService.findById(id);
		//更改返回前端的日期格式
		Date birthday = employee.getBirthday();
		MySimpleDateFormat sdf = new MySimpleDateFormat();
		java.sql.Date time = sdf.dateToFormatDate(birthday);
		employee.setBirthday(time);
		model.addAttribute("employee",employee);
		List<JobInf> jobList = jobService.findAll();
		model.addAttribute("jobs", jobList);
		List<DeptInf> deptList = deptService.findAll();
		model.addAttribute("depts", deptList);
		return "employee/employeeedit";
	}
	
	@RequestMapping("/saveorupdate.action")
	public String addOrUpdate(EmployeeInf employee,Integer dept_id,Integer job_id){
		employee.setDeptId(dept_id);
		employee.setJobId(job_id);
		if (employee.getId()==null) {
			employeeService.save(employee);
		} else {
			employeeService.update(employee);
		}
		return "redirect:/employee/list.action";
	}
	
	@RequestMapping("/del.action")
	public String del(Integer[] employeeIds){
		for (Integer id : employeeIds) {
			employeeService.delete(id);
		}
		return "redirect:/employee/list.action";
	}
}
