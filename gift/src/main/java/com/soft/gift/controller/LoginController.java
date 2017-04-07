package com.soft.gift.controller;

import com.soft.gift.model.User;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	public String login(){
		return "templates/login";
	}
	

	@RequestMapping(value="userlogin.do")
	public void loginCheck(HttpServletRequest request,HttpServletResponse response,String account,String password) throws IOException{
		String status = userService.ifLogin(account, password);
		if ("登陆成功".equals(status)){
			User user = userService.getUser(account);
			UserInfo userInfo = userService.getUserInfo(account);
			request.getSession().setAttribute("userInfo", userInfo);
			request.getSession().setAttribute("user", user);
		}
		PrintWriter writer = response.getWriter();
		writer.print(status);
		writer.close();
	}
	
	@RequestMapping(value="register.do")
	public void register(HttpServletRequest request,HttpServletResponse response,String account,String password,String nickname) throws IOException{
		String status = userService.register(account, password, nickname);
		PrintWriter writer = response.getWriter();
		writer.print(status);
		writer.close();
	}
}
