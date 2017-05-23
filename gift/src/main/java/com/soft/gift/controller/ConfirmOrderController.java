package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConfirmOrderController{
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="/confirm_order")
	public String confirmOrder(HttpServletRequest request, Integer gift_id, Integer num, String spec, ModelMap mv){
		String [] spec_ids = spec.split(",");
		List<Spec> specs = new ArrayList<>();
		for (String string : spec_ids) {
			Integer spec_id = Integer.parseInt(string);
			Spec spec2 = giftService.getSpecBySpecID(spec_id);
			specs.add(spec2);
		}
		Gift gift = giftService.getGiftByID(gift_id);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		System.out.println(userInfo);
		List<ShippingAddress> addresses = new ArrayList<>();
		addresses = giftService.getAddressByAccount(userInfo.getAccount());
		mv.put("addresss",addresses);
		mv.put("gift",gift);
		mv.put("specs",specs);
		System.out.println(specs);
		mv.put("num",num);
		return "confirm_order";
	}
	
	@RequestMapping(value="/confirm_order_sh")
	public String confirmOrderSh(HttpServletRequest request, String shArr,ModelMap mv){
		System.out.println("shArr:"+shArr);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<ShippingAddress> addresses = giftService.getAddressByAccount(userInfo.getAccount());
		String[] sh_ids = shArr.split(",");
		Map<LargeShopCart, List<Spec>> map = new HashMap<>();
		Double total_price = 0.0;
		for (String sh_id : sh_ids) {
			ShoppingCart sh = giftService.getShopCartByID(sh_id);
			Gift gift = giftService.getGiftByID(sh.getGift_id());
			total_price += sh.getPrice();
			LargeShopCart largeShopCart = new LargeShopCart(sh.getId(), userInfo.getAccount(), sh.getGift_id(), sh.getNum(), sh.getAdd_time(), sh.getPrice(), gift.getName(), gift.getPrice(), gift.getSmall_img(), gift.getStock());
			List<Spec> specs = giftService.getShSpecByShID(sh_id);
			map.put(largeShopCart, specs);
		}
		mv.put("total_price",total_price);
		mv.put("addresss",addresses);
		mv.put("map",map);
		return "confirm_order_sh";
	}
}
