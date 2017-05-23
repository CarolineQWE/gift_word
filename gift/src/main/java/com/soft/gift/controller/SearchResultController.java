package com.soft.gift.controller;

import com.alibaba.fastjson.JSON;
import com.soft.gift.model.Category;
import com.soft.gift.model.Gift;
import com.soft.gift.model.Strategy;
import com.soft.gift.service.GiftService;
import com.soft.gift.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchResultController {
	@Autowired
	private GiftService giftService;
	@Autowired
	private StrategyService strategyService;

	@RequestMapping(value="/CateID")
	public String searchGiftByCateID(HttpServletRequest request, ModelMap map, Integer cate_id){
		List<Gift> gifts = giftService.searchGiftByCateID(cate_id);
		Category c = giftService.getCateByID(cate_id);
		Category p_cate = giftService.getCateByID(c.getP_id());
		List<Category> categories = giftService.getSecondMenu(p_cate.getId());
		map.put("gifts",gifts);
		map.put("large_cate",p_cate);
		map.put("categories",categories);
		return "search_result_cate";
	}

	@ResponseBody
	@RequestMapping(value="/CateID/inner")
	public String searchGiftByCateIDInner(HttpServletRequest request,Integer cate_id){
		List<Gift> gifts = giftService.searchGiftByCateID(cate_id);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/CateIDOrderBySale")
	public String searchGiftByCateIDOrderBySale(HttpServletRequest request,Integer cate_id){
		List<Gift> gifts = giftService.searchGiftByCateIDOrderBySale(cate_id);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/CateIDOrderByHot")
	public String searchGiftByCateIDOrderByHot(HttpServletRequest request, Integer cate_id){
		List<Gift> gifts = giftService.searchGiftByCateIDOrderByHot(cate_id);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/CateIDOrderByPrice")
	public String searchGiftByCateIDOrderByPrice(HttpServletRequest request, Integer cate_id, Integer min,Integer max){
		List<Gift> gifts = giftService.searchGiftByCateIDOrderByPrice(cate_id,min,max);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@RequestMapping(value="/LargeCate")
	public String searchGiftByLargeCate(HttpServletRequest request, ModelMap map,Integer large_cate){
		List<Gift> gifts = giftService.searchGiftByLargeCate(large_cate);
		Category category = giftService.getCateByID(large_cate);
		List<Category> categories = giftService.getSecondMenu(large_cate);
		map.put("gifts",gifts);
		map.put("large_cate",category);
		map.put("categories",categories);
		return "search_result_cate";
	}

	@ResponseBody
	@RequestMapping(value="/LargeCate/inner")
	public String searchGiftByLargeCateAll(HttpServletRequest request, Integer large_cate){
		List<Gift> gifts = giftService.searchGiftByLargeCate(large_cate);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/LargeCateOrderBySale")
	public String searchGiftByLargeCateOrderBySale(HttpServletRequest request, Integer large_cate){
		List<Gift> gifts = giftService.searchGiftByLargeCateOrderBySale(large_cate);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/LargeCateOrderByHot")
	public String searchGiftByLargeCateOrderByHot(HttpServletRequest request, Integer large_cate){
		List<Gift> gifts = giftService.searchGiftByLargeCateOrderByHot(large_cate);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/LargeCateOrderByPrice")
	public String searchGiftByLargeCateOrderByPrice(HttpServletRequest request, Integer large_cate,Integer min,Integer max){
		List<Gift> gifts = giftService.searchGiftByLargeCateOrderByPrice(large_cate,min,max);
		String json = JSON.toJSONString(gifts);
		return json;
	}

	@RequestMapping(value="/Keyword")
	public String searchStrategyByKeyword(HttpServletRequest request,ModelMap map, String keyword){
		List<Gift> gifts = giftService.searchGiftByKeyword(keyword);
		List<Strategy> strategies = strategyService.searchStrategyByKeyword(keyword);
		map.put("gifts",gifts);
		map.put("strategies",strategies);
		return "search_result";
	}


}
