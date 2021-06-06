package com.multi.oauth10server.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.oauth10server.model.UsersVO;
import com.multi.oauth10server.service.UsersService;
import com.multi.oauth10server.util.SessionUtil;

@Controller
@RequestMapping(value="/login") 
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping
	public String redirectLoginPage() {
		return "login";
	}
	
	@PostMapping
	public ModelAndView loginProcess(UsersVO usersVO, HttpSession session, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		UsersVO vo  = usersService.selectUsers(usersVO);
		if (vo != null) {
			SessionUtil.loginUser(session, vo);
			response.sendRedirect("viewAppList");
		} else {
			mav.addObject("isLogin", false);
			mav.setViewName("login");
		}
		return mav;
	}
}
