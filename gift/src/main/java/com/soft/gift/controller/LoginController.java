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
		return "login";
	}
	

}
