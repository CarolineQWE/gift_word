package com.soft.gift.controller;

import com.soft.gift.model.Category;
import com.soft.gift.model.Gift;
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
public class IndexController {
	
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, ModelMap map){
		Map<Category, List<Category>> menuMap = giftService.getMallMenu();
		List<Gift> latestGift = new ArrayList<Gift>();
		latestGift = giftService.getLastedGift();
		List<Gift> hotGift = new ArrayList<Gift>();
		hotGift = giftService.getHotGift();
		map.put("menuMap",menuMap);
		map.put("latestGift",latestGift);
		map.put("hotGift",hotGift);
		return "index";
	}
	
	@RequestMapping(value="/index_admin")
	public String index_admin(HttpServletRequest request,ModelMap map){
		List<Gift> gifts = giftService.getHotGift();
		Map<Gift,String> giftMap = new HashMap<>();
		for (Gift gift:gifts) {
			String cate = giftService.getCateByID(gift.getCategory());
			giftMap.put(gift,cate);
		}
		map.put("giftMap",giftMap);
		return "index_admin";
	}
}
