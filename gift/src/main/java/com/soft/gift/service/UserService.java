package com.soft.gift.service;

import com.soft.gift.model.User;
import com.soft.gift.model.UserInfo;

public interface UserService {
	public String ifLogin(String account, String password);
	public String register(String account, String password, String nickname);
	
	public UserInfo getUserInfo(String account);
	public User getUser(String account);
	
}
