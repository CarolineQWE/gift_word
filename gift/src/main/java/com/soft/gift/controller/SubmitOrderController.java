package com.soft.gift.controller;

import com.soft.gift.model.UserInfo;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SubmitOrderController {
	@Autowired
	private GiftService giftService;
	
	@ResponseBody
	@RequestMapping(value="/submit_order")
	public String submitOrder(HttpServletRequest request,HttpServletResponse response,Integer address_id,String sharr,Integer status) throws IOException {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<sharr:"+sharr);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<address_id:"+address_id);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		giftService.addOrder(sharr, userInfo.getAccount(), address_id, status);
		return "成功";
	}
	
	@RequestMapping(value="/submit_order_single")
	public String submitOrderSingle(HttpServletRequest request, Integer address_id, String spec_ids, Integer num, Integer gift_id, Integer status) throws IOException{
		System.out.println(status);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		giftService.addSingleOrder(userInfo.getAccount(),address_id,spec_ids,num,gift_id,status);
		if (status == 0) {
			return "nopay";
		}else {
			return "success";
		}
	}
	
}
