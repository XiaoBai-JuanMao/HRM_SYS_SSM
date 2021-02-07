package com.gec.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gec.pojo.UserInf;

public class UserInterceptor implements HandlerInterceptor {

	//最后执行
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	//完成请求后
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//进入controller层前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		HttpSession session = request.getSession();
		UserInf user = (UserInf) session.getAttribute("user_session");
		if (user!=null) {
			return true;	//放行
		}else {
			request.setAttribute("message", "用户未登录");
			request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);;
			return false;	//不放行
		}
	}

}
