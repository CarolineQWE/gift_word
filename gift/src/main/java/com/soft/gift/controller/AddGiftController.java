package com.soft.gift.controller;

import com.alibaba.fastjson.JSON;
import com.soft.gift.model.Category;
import com.soft.gift.model.Spec;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AddGiftController {
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="addGift.do")
	public ModelAndView addGift(HttpServletRequest request){
		ModelAndView mView = new ModelAndView("addGift");
		List<Category> categories = new ArrayList<Category>();
		categories = giftService.getFirstCate();
		Map<String,List<Spec>> saleSpecs= giftService.getSpecBySpecName(0);
		Map<String,List<Spec>> baseSpecs = giftService.getSpecBySpecName(1);
		mView.addObject("categories",categories);
		mView.addObject("saleSpecs",saleSpecs);
		mView.addObject("baseSpecs",baseSpecs);
		return mView;
	}
	
	@ResponseBody
	@RequestMapping(value="getSecondMenu.do")
	public String getSecondMenu(HttpServletRequest request,HttpServletResponse response,Integer id) throws IOException{
		List<Category> categories = giftService.getSecondMenu(id);
		String json = JSON.toJSONString(categories);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.close();
		return json;
	}
	
	@RequestMapping(value="submitGift.do")
	public void submitGift(HttpServletRequest request,String string) throws IOException{
		System.out.println(string);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		/*Gift gift = new Gift(name, 0, 0, stock, price, new Timestamp(new Date().getTime()), 0, small_img, category);
		GiftInfo giftInfo = new GiftInfo(gift_id, brief, gift_info);
		GiftSpec giftSpec = new GiftSpec(gift_id, spec_id);
		giftService.addGift();*/
	}
}
