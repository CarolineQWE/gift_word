package com.soft.gift.controller;

import com.soft.gift.model.Gift;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchResultController {
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="searchGiftByCateID.do")
	public ModelAndView searchGiftByCateID(HttpServletRequest request, Integer cate_id){
		List<Gift> gifts = giftService.searchGiftByCateID(cate_id);
		ModelAndView mv = new ModelAndView("search_result");
		mv.addObject("gifts",gifts);
		return mv;
	}
	
	@RequestMapping(value="searchGiftByKeyWord.do")
	public ModelAndView searchGiftByKeyWord(HttpServletRequest request, String keyword){
		List<Gift> gifts = giftService.searchGiftByKeyword(keyword);
		ModelAndView mv = new ModelAndView("search_result");
		mv.addObject("gifts",gifts);
		return mv;
	}
}
