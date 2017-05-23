package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {
	@Autowired
	private GiftService giftService;
	
	@ResponseBody
	@RequestMapping(value="/addShoppingCart")
	public String addShoppingCart(HttpServletRequest request,HttpServletResponse response,
			Integer gift_id,Integer num,String spec) throws IOException{
		List<Integer> spec_ids_int = new ArrayList<>();
		String [] spec_ids = spec.split(",");
		for (String string : spec_ids) {
			Integer spec_id = Integer.parseInt(string);
			spec_ids_int.add(spec_id);
		}
		Gift gift = giftService.getGiftByID(gift_id);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String account = userInfo.getAccount();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = simpleDateFormat.format(timestamp);
		String sc_id = time+account;
		Double price = gift.getPrice();
		ShoppingCart shoppingCart = new ShoppingCart(sc_id, account, gift_id, num, timestamp, price);
		giftService.addShoppingCart(shoppingCart);
		ScSpec scSpec = null;
		for (Integer spec3 : spec_ids_int) {
			scSpec = new ScSpec(sc_id, spec3);
			giftService.insertScSpec(scSpec);
		}
		return "成功";
	}
	
	@RequestMapping(value="/getShoppingCart")
	public String getShoppingCart(HttpServletRequest request, ModelMap mv){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<LargeShopCart, List<Spec>> shMap = new HashMap<>();
		shMap = giftService.getShopCartAllInfo(userInfo.getAccount());
		mv.put("shMap",shMap);
		mv.put("size",shMap.size());
		return "shoppingCart";
	}

	@ResponseBody
	@RequestMapping(value="/deletesh")
	public void addShoppingCart(HttpServletRequest request,String sh_id){
		giftService.deleteSh(sh_id);
	}

	@ResponseBody
	@RequestMapping(value="/modifysh")
	public void modifySh(HttpServletRequest request,String sh_id,Integer new_num){
		giftService.modifySh(sh_id,new_num);
	}
}
