package com.gec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginFormController {
	
	@RequestMapping("/loginForm.do")
	public String loginForm(String message,Model model) throws Exception{
		model.addAttribute("message", message);
		return "loginForm";
	}
	
	@RequestMapping("/main.do")
	public String sendMain() throws Exception{
		return "main";
	}
	
	@RequestMapping("/left.do")
	public String sendLeft() throws Exception{
		return "left";
	}
	@RequestMapping("/right.do")
	public String sendRight() throws Exception{
		return "right";
	}
	@RequestMapping("/top.do")
	public String sendTop() throws Exception{
		return "top";
	}
}
