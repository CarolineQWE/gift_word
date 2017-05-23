package com.soft.gift.controller;

import com.alibaba.fastjson.JSON;
import com.soft.gift.model.*;
import com.soft.gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/manageAddress")
	public String manageAddress(HttpServletRequest request,ModelMap map){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<ShippingAddress> addresses = userService.getMyAddress(userInfo.getAccount());
		System.out.println("addresss"+addresses);
		map.put("addresses",addresses);
		return "manageAddress";
	}

	@ResponseBody
	@RequestMapping(value = "/addAddress")
	public String addAddress(HttpServletRequest request,String prov,String city,String dist,String tel,String receiver,String address_info){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		ShippingAddress address = new ShippingAddress(userInfo.getAccount(),prov,city,dist,tel,receiver,address_info);
		userService.addAddress(address);
		return "成功";
	}

	@ResponseBody
	@RequestMapping(value = "/getAddress")
	public String getAddress(HttpServletRequest request,Integer id){
		ShippingAddress address = userService.getAddressByID(id);
		String json = JSON.toJSONString(address);
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/modifyAddress")
	public String modifyAddress(HttpServletRequest request,String prov,String city,String dist,String tel,String receiver,String address_info){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		ShippingAddress address = new ShippingAddress(userInfo.getAccount(),prov,city,dist,tel,receiver,address_info);
		userService.modifyAddress(address);
		return "成功";
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAddress")
	public String deleteAddress(Integer id){
		userService.deleteAddress(id);
		return "成功";
	}

	@RequestMapping(value = "/personal")
	public String personal(HttpServletRequest request,ModelMap map){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		userInfo = userService.getUserInfo(userInfo.getAccount());
		map.put("user",userInfo);
		return "personal";
	}

	@ResponseBody
	@RequestMapping(value = "/personal_info_modify")
	public String personal_info_modify(HttpServletRequest request,ModelMap map,String account,String head,String nickname,Integer age,String birthday,String brief,String tel,Integer sex,String prov,String city,String dist) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(birthday);
		Date date1 = new Date(date.getTime());
		UserInfo userInfo = new UserInfo(account,nickname,age,sex,prov,city,dist,date1,head,tel,brief);
		userService.modifyUserInfo(userInfo);
		return "成功";
	}

	@RequestMapping(value = "/myStrategy")
	public String myStrategy(HttpServletRequest request,ModelMap map){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<Strategy> strategies = userService.getMyStrategy(userInfo.getAccount());
		map.put("strategies",strategies);
		return "myStrategy";
	}

	@RequestMapping(value = "/myCollection")
	public String myCollection(HttpServletRequest request,ModelMap map){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<Gift> collectedGifts = userService.getCollectedGift(userInfo.getAccount());
		List<Strategy> strategyMap = userService.getCollectedStra(userInfo.getAccount());
		map.put("collectedGifts",collectedGifts);
		map.put("collectedStras",strategyMap);
		return "myCollection";
	}

	@ResponseBody
	@RequestMapping(value = "/addCollection")
	public String addCollection(HttpServletRequest request,Integer collected_id,Integer type){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Collections collection = new Collections(collected_id,type,userInfo.getAccount());
		userService.addCollection(collection);
		return "成功";
	}


	@ResponseBody
	@RequestMapping(value = "/addCare")
	public String addCare(HttpServletRequest request,String cared_account){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Care care = new Care(cared_account,userInfo.getAccount());
		userService.addCare(care);
		User user = userService.getUser(userInfo.getAccount());
		user.setCare(user.getCare()+1);
		userService.updateUser(user);
		return "成功";
	}

	@ResponseBody
	@RequestMapping(value = "/deleteCare")
	public String deleteCare(HttpServletRequest request,String cared_account){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Care care = new Care(cared_account,userInfo.getAccount());
		userService.deleteCare(care);
		User user = userService.getUser(userInfo.getAccount());
		user.setCare(user.getCare()-1);
		userService.updateUser(user);
		return "成功";
	}

	@RequestMapping(value = "/myCare")
	public String MyCareUser(HttpServletRequest request,ModelMap map){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<UserInfo> myCareUsers = userService.getMyCareUser(userInfo.getAccount());
		List<UserInfo> myFans = userService.getMyFans(userInfo.getAccount());
		map.put("myCareUsers",myCareUsers);
		map.put("myFans",myFans);
		return "myCare";
	}

}
