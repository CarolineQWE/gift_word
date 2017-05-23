package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.*;
import com.soft.gift.model.*;
import com.soft.gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserInfoDAO userInfoDAO;
	@Autowired
	private ShippingAddressDAO shippingAddressDAO;
	@Autowired
	private CollectionsDAO collectionsDAO;
	@Autowired
	private CareDAO careDAO;
	@Autowired
	private StrategyDAO strategyDAO;
	@Autowired
	private GiftDAO giftDAO;

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
			User user2 = new User(account,password,0,0,0);
			UserInfo userInfo = new UserInfo();
			userInfo.setAccount(account);
			userInfo.setNickname(nickname);
			userInfo.setHead("../static/img/moren_head.jpg");
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

	@Override
	public void modifyUserInfo(UserInfo userInfo) {
		userInfoDAO.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public List<ShippingAddress> getMyAddress(String account) {
		List<ShippingAddress> addresses = new ArrayList<>();
		ShippingAddress address = new ShippingAddress();
		address.setAccount(account);
		addresses = shippingAddressDAO.select(address);
		return addresses;
	}

	@Override
	public void addAddress(ShippingAddress address) {
		shippingAddressDAO.insert(address);
	}

	@Override
	public void modifyAddress(ShippingAddress address) {
		shippingAddressDAO.updateByAccount(address);
	}

	@Override
	public void deleteAddress(Integer id) {
		shippingAddressDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<Strategy> getMyStrategy(String account) {
		Strategy strategy = new Strategy();
		strategy.setAccount(account);
		List<Strategy> strategies = strategyDAO.select(strategy);
		return strategies;
	}

	@Override
	public List<Gift> getCollectedGift(String account) {
		Collections collection = new Collections();
		collection.setAccount(account);
		collection.setCollected_type(0);
		List<Collections> collectedGifts = collectionsDAO.select(collection);
		List<Gift> gifts = new ArrayList<>();
		for(Collections c:collectedGifts){
			Gift gift = giftDAO.selectByPrimaryKey(c.getCollected_id());
			gifts.add(gift);
		}
		return gifts;
	}

	@Override
	public List<Strategy> getCollectedStra(String account) {
		Collections collection = new Collections();
		collection.setAccount(account);
		collection.setCollected_type(1);
		List<Collections> collectedStra = collectionsDAO.select(collection);
		List<Strategy> strategies = new ArrayList<>();
		for(Collections c:collectedStra){
			Strategy strategy = strategyDAO.selectByPrimaryKey(c.getCollected_id());
			strategies.add(strategy);
		}
		return strategies;
	}

	@Override
	public void addCollection(Collections collection) {
		collectionsDAO.insert(collection);
	}

	@Override
	public List<UserInfo> getMyCareUser(String account) {
		Care care = new Care();
		care.setAccount(account);
		List<Care> cares = careDAO.select(care);
		List<UserInfo> userInfos = new ArrayList<>();
		for (Care c:cares){
			UserInfo userInfo = userInfoDAO.selectByPrimaryKey(c.getCared_account());
			userInfos.add(userInfo);
		}
		return userInfos;
	}

	@Override
	public List<UserInfo> getMyFans(String account) {
		Care care = new Care();
		care.setCared_account(account);
		List<Care> cares = careDAO.select(care);
		List<UserInfo> userInfos = new ArrayList<>();
		for (Care c:cares){
			UserInfo userInfo = userInfoDAO.selectByPrimaryKey(c.getAccount());
			userInfos.add(userInfo);
		}
		return userInfos;
	}

	@Override
	public ShippingAddress getAddressByID(Integer id) {
		ShippingAddress address = shippingAddressDAO.selectByPrimaryKey(id);
		return address;
	}

	@Override
	public void addCare(Care care) {
		careDAO.insert(care);
	}

	@Override
	public void deleteCare(Care care) {
		careDAO.deleteCareByAccountAndCaredAccount(care);
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUserCare(user);
	}
}
