package com.soft.gift.controller;

import com.soft.gift.model.User;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/ifUserExist")
	public String ifUserExist(String account){
		boolean flag = userService.checkAccount(account);
		if (flag){
			return "用户已存在";
		}else{
			return "用户不存在";
		}
	}

	@RequestMapping(value = "/loginCheck")
	@ResponseBody
	public String loginCheck(HttpServletRequest request,HttpServletResponse response,String account, String password) throws IOException {
		String status = userService.ifLogin(account,password);
		if(status.equals("登录成功")){
			User user = userService.getUser(account);
			UserInfo userInfo = userService.getUserInfo(account);
			request.getSession().setAttribute("userInfo",userInfo);
			return String.valueOf(user.getIdentity());
		}else {
			return status;
		}
	}

	@ResponseBody
	@RequestMapping(value="/register")
	public String register(HttpServletRequest request,HttpServletResponse response,String account,String password,String nickname) throws IOException{
		String status = userService.register(account, password, nickname);
		return status;
	}
}
