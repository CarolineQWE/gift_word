package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.CommentService;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class GiftInfoController {
	@Autowired
	private GiftService giftService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="giftInfo.do")
	public ModelAndView giftInfo(HttpServletRequest request, Integer gift_id) {
		Gift gift = giftService.getGiftByID(gift_id);
		GiftInfo giftInfo = giftService.getGigtInfoByID(gift_id);
		List<Spec> saleSpecMap = giftService.getGiftSpecByGiftIDAndType(gift_id, 0);
		List<Spec> baseSpecMap = giftService.getGiftSpecByGiftIDAndType(gift_id, 1);
		List<SpecNum> specNums = giftService.getSpecNum(gift_id);
		int specTypeNum = specNums.size();
		Map<Comment, UserInfo> comments = commentService.getCommentByGiftID(gift_id);
		Double avg = commentService.getAverageSorceByGiftID(gift_id);
		List<Integer> nums = commentService.getCommentNum(gift_id);
		ModelAndView mv = new ModelAndView("giftInfo");
		mv.addObject("gift",gift);
		mv.addObject("giftInfo",giftInfo);
		mv.addObject("saleSpecMap",saleSpecMap);
		mv.addObject("baseSpecMap",baseSpecMap);
		mv.addObject("specNums",specNums);
		mv.addObject("specTypeNum",specTypeNum);
		mv.addObject("comments",comments);
		mv.addObject("avg",avg);
		mv.addObject("scoreNums",nums);
		return mv;
	}
	
}
