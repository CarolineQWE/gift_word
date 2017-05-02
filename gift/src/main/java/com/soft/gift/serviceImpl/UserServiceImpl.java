package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.UserDAO;
import com.soft.gift.mapper.UserInfoDAO;
import com.soft.gift.model.User;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserInfoDAO userInfoDAO;

    @Override
    public boolean checkAccount(String account) {
        User user = new User();
        user.setAccount(account);
        User user2 = null;
        user2 = userDAO.selectOne(user);
        System.out.println("user2:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+user2);
        if(user2 == null){
            return true;
        }else {
            return false;
        }
    }

	@Override
	public String ifLogin(String account, String password) {
		System.out.println(account);
		System.out.println(password);
		User user = new User();
		user.setAccount(account);
		User user2 = userDAO.selectOne(user);
		System.out.println("user2:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+user2);
		if(user2 == null){
			return "用户不存在";
		}else if(password.equals(user2.getPassword())){
			System.out.println(user2);
			System.out.println(user2.getPassword());
			return "登录成功";
		}else {
			return "密码错误";
		}
	}

	@Override
	public String register(String account, String password, String nickname) {
		User user = new User();
		System.out.println(account);
		user.setAccount(account);
		User user3 = userDAO.selectOne(user);
		System.out.println(user3);
		if( !(user3 == null || user3.equals(""))){
			return "用户已存在";
		}else{
			User user2 = new User(account,password,0,0);
			UserInfo userInfo = new UserInfo();
			userInfo.setAccount(account);
			userInfo.setNickname(nickname);
			int n1 = userDAO.insert(user2);
			int n2 = userInfoDAO.insert(userInfo);
			if(n1 == 1 && n2 ==1){
				return "注册成功";
			}else{
				return "注册失败";
			}
		}
	}

	@Override
	public UserInfo getUserInfo(String account) {
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount(account);
		return userInfoDAO.selectOne(userInfo);
	}

	@Override
	public User getUser(String account) {
		User user = new User();
		user.setAccount(account);
		return userDAO.selectOne(user);
	}

	
	
}
