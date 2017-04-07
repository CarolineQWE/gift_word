package com.soft.gift.controller;

import com.soft.gift.model.Category;
import com.soft.gift.model.Gift;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
	
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, ModelMap map){
		Map<Category, List<Category>> menuMap = giftService.getMallMenu();
		List<Gift> latestGift = new ArrayList<>();
		latestGift = giftService.getLastedGift();
		List<Gift> hotGift = new ArrayList<>();
		hotGift = giftService.getHotGift();
		map.put("menuMap",menuMap);
		map.put("latestGift",latestGift);
		map.put("hotGift",hotGift);
		return "index";
	}
	
	@RequestMapping(value="/index_admin.do")
	public ModelAndView index_admin(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("index_admin");
		Map<Category, List<Category>> menuMap = giftService.getMallMenu();
		List<Gift> latestGift = new ArrayList<>();
		latestGift = giftService.getLastedGift();
		List<Gift> hotGift = new ArrayList<>();
		hotGift = giftService.getHotGift();
		mv.addObject("menuMap",menuMap);
		mv.addObject("latestGift",latestGift);
		mv.addObject("hotGift",hotGift);
		return mv;
	}
}
