package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.CommentService;
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
public class GiftInfoController {
	@Autowired
	private GiftService giftService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/gift/giftInfo")
	public String giftInfo(HttpServletRequest request, Integer gift_id, ModelMap mv) {
		Gift gift = giftService.getGiftByID(gift_id);
		System.out.print("gift_id"+gift_id);
		GiftInfo giftInfo = giftService.getGigtInfoByID(gift_id);
		List<Spec> saleSpecMap = giftService.getGiftSpecByGiftIDAndType(gift_id, 0);
		System.out.println(saleSpecMap);
		List<Spec> baseSpecMap = giftService.getGiftSpecByGiftIDAndType(gift_id, 1);
		List<SpecNum> specNums = giftService.getSpecNum(gift_id);
		System.out.println(specNums);
		int specTypeNum = specNums.size();
		System.out.println(specTypeNum);
		Map<String,List<Spec>> classifiedSpecMap = new HashMap<>();
		int i = 0;
		for (int j = 0; j < specTypeNum; j++){
			List<Spec> oneSpecName = new ArrayList<>();
			oneSpecName = saleSpecMap.subList(i ,i+specNums.get(j).getSpec_num());
			i += specNums.get(j).getSpec_num();
			classifiedSpecMap.put(specNums.get(j).getSpec_name(),oneSpecName);
		}
		System.out.println(classifiedSpecMap);
		List<Comment> comments = commentService.getCommentByGiftID(gift_id);
		Double avg = commentService.getAverageSorceByGiftID(gift_id);
		List<Integer> nums = commentService.getCommentNum(gift_id);
		mv.put("gift",gift);
		mv.put("giftInfo",giftInfo);
		mv.put("classifiedSpecMap",classifiedSpecMap);
		mv.put("baseSpecMap",baseSpecMap);
		mv.put("comments",comments);
		mv.put("avg",avg);
		mv.put("scoreNums",nums);
		return "giftInfo";
	}


}
