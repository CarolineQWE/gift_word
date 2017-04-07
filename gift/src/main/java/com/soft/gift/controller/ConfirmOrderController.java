package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConfirmOrderController{
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="confirm_order.do")
	public ModelAndView confirmOrder(HttpServletRequest request, Integer gift_id, Integer num, String spec){
		String [] spec_ids = spec.split(",");
		List<Spec> specs = new ArrayList<>();
		for (String string : spec_ids) {
			Integer spec_id = Integer.parseInt(string);
			Spec spec2 = giftService.getSpecBySpecID(spec_id);
			specs.add(spec2);
		}
		ModelAndView mv = new ModelAndView("confirm_order");
		Gift gift = giftService.getGiftByID(gift_id);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		System.out.println(userInfo);
		List<ShippingAddress> addresses = new ArrayList<>();
		addresses = giftService.getAddressByAccount(userInfo.getAccount());
		mv.addObject("addresss",addresses);
		mv.addObject("gift",gift);
		mv.addObject("specs",specs);
		System.out.println(specs);
		mv.addObject("num",num);
		return mv;
	}
	
	@RequestMapping(value="confirm_order_sh.do")
	public ModelAndView confirmOrderSh(HttpServletRequest request, String shArr){
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
		ModelAndView mv = new ModelAndView("confirm_order_sh");
		mv.addObject("total_price",total_price);
		mv.addObject("addresss",addresses);
		mv.addObject("map",map);
		return mv;
	}
}
