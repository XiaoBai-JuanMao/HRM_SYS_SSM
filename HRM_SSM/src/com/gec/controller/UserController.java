package com.gec.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gec.pojo.UserInf;
import com.gec.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.action")
	public String login(String loginname,String password,HttpSession session,
							  Model model){
		UserInf user = userService.login(loginname, password);
		if (user!=null) {
			session.setAttribute("user_session", user);
			//如果是后台跳转，需使用到关键字 redirect：/main.do
			return "redirect:/main.do";
		} else {
			model.addAttribute("message", "用户名或密码错误");
			return "redirect:/loginForm.do";
		}
	}
	
	@RequestMapping("/logout.action")
	public String logout(HttpSession session, Model model){
		//清除session
		session.invalidate();
		return "redirect:/loginForm.do";
	}
	
	@RequestMapping("/list.action")
	public String list(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
						 	 UserInf user,Model model){
		model.addAttribute("user", user);
		PageInfo<UserInf> info = userService.findPageEntity(pageNum, user);
		model.addAttribute("pageModel", info);
		return "user/userlist";
	}
	
	@RequestMapping("/update.do")
	public String updateView(Integer id,Model model){
		UserInf user = userService.findById(id);
		model.addAttribute("user",user);
		return "user/useredit";
	}
	
	@RequestMapping("/add.do")
	public String addView(Model model){
		return "user/useredit";
	}
	
	@RequestMapping("/loginnameajax.action")
	@ResponseBody
	public UserInf loginnameAjax(String loginname,HttpServletResponse response) throws IOException{
		UserInf user = userService.findByLoginname(loginname);
		return user;
	}
	
	@RequestMapping("/addoredit.action")
	public String update(UserInf user,Model model){
		if(user.getId()!=null){
			userService.update(user);
		}else {
			userService.save(user);
		}
		return "redirect:/user/list.action";
	}
	
	@RequestMapping("/del.action")
	public String del(Integer[] userIds,HttpSession session,Model model){
		UserInf loginUser = (UserInf) session.getAttribute("user_session");
		for (Integer id : userIds) {
			//判断是否为当前登录id
			if (id != loginUser.getId()) {
				userService.delete(id);							
			}
		}
		return "redirect:/user/list.action";
	}
}
