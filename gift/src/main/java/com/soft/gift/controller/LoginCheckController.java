package com.soft.gift.controller;

import com.soft.gift.model.User;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fyq on 2017/4/11.
 */
@RestController
public class LoginCheckController {
    @Autowired
    public UserService userService;

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
        if (status.equals("用户不存在")){
            return "用户不存在";
        }else if(status.equals("密码错误")){
            return "密码错误";
        }else if(status.equals("登录成功")){
            User user = userService.getUser(account);
            UserInfo userInfo = userService.getUserInfo(account);
            request.getSession().setAttribute("userInfo",userInfo);
            return String.valueOf(user.getIdentity());
        }else {
            return "登录异常";
        }
    }

}
