package com.soft.gift.service;

import com.soft.gift.model.*;

import java.util.List;

public interface UserService {
	public boolean checkAccount(String account);
    public String ifLogin(String account, String password);
	public String register(String account, String password, String nickname);
	
	public UserInfo getUserInfo(String account);
	public User getUser(String account);

    public void modifyUserInfo(UserInfo userInfo);

	public List<ShippingAddress> getMyAddress(String account);

	public void addAddress(ShippingAddress address);

	public void modifyAddress(ShippingAddress address);

	public void deleteAddress(Integer id);

	public List<Strategy> getMyStrategy(String account);

	public List<Gift> getCollectedGift(String account);

	public List<Strategy> getCollectedStra(String account);

	public void addCollection(Collections collection);

	public List<UserInfo> getMyCareUser(String account);

	public List<UserInfo> getMyFans(String account);

    public ShippingAddress getAddressByID(Integer id);

	public void addCare(Care care);

	public void deleteCare(Care care);

	public void updateUser(User user);

    public void modifyAddressById(ShippingAddress address);
}
