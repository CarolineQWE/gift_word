package com.soft.gift.controller;

import com.alibaba.fastjson.JSON;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AddGiftController {
	@Autowired
	private GiftService giftService;
	
	@RequestMapping(value="/gift/addGift")
	public String addGift(HttpServletRequest request, ModelMap map){
		List<Category> categories = new ArrayList<Category>();
		categories = giftService.getFirstCate();
		Map<String,List<Spec>> saleSpecs= giftService.getSpecBySpecName(0);
		Map<String,List<Spec>> baseSpecs = giftService.getSpecBySpecName(1);
		map.put("categories",categories);
		map.put("saleSpecs",saleSpecs);
		map.put("baseSpecs",baseSpecs);
		return "addGift";
	}
	
	@ResponseBody
	@RequestMapping(value="/gift/getSecondMenu")
	public String getSecondMenu(HttpServletRequest request,HttpServletResponse response,Integer id) throws IOException{
		List<Category> categories = giftService.getSecondMenu(id);
		String json = JSON.toJSONString(categories);
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/gift/submitGift")
	public String submitGift(HttpServletRequest request,String title,String brief,String src1,String src2,String src3,
							 Integer cate_id,Integer second_cate_id,Integer stock,Double price,String content,String spec_ids,String base_spec_ids,
							 Integer if_custom_made) throws IOException{

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (second_cate_id == null && cate_id != null){
			second_cate_id = cate_id;
		}
		Gift gift = new Gift(title,0,0,stock,price,timestamp,0,src1,src2,src3,if_custom_made,second_cate_id,0);
		System.out.println("gift:"+gift);
		giftService.addGift(gift);//插入礼物表
		GiftInfo giftInfo = new GiftInfo(gift.getId(),brief,content);
		giftService.addGiftInfo(giftInfo);//插入礼物详情表
		String [] arr  = spec_ids.split(",");
		for(String s:arr){
			Integer spec_id = Integer.parseInt(s);
			GiftSpec giftSpec = new GiftSpec(gift.getId(), spec_id);
			giftService.addGiftSpec(giftSpec);//插入礼物属性对应表
		}
		String [] arr_base = base_spec_ids.split(",");
		for (String s:arr_base){
			Integer spec_id = Integer.parseInt(s);
			GiftSpec giftSpec = new GiftSpec(gift.getId(), spec_id);
			giftService.addGiftSpec(giftSpec);//插入礼物属性对应表
		}
		return "成功";

	}

	@ResponseBody
	@RequestMapping(value="/spec/addSaleSpec")
	public String addSaleSpec(HttpServletRequest request,String name,String value){
		Spec spec = new Spec(name,value,0);
		giftService.addSaleSpec(spec);
		return "成功";
	}

	@ResponseBody
	@RequestMapping(value="/spec/addBaseSpec")
	public String addBaseSpec(HttpServletRequest request,String name,String value){
		Spec spec = new Spec(name,value,1);
		giftService.addBaseSpec(spec);
		return "成功";
	}
}
