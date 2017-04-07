package com.soft.gift.controller;

import com.soft.gift.model.UserInfo;
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

@Controller
public class SubmitOrderController {
	@Autowired
	private GiftService giftService;
	
	@ResponseBody
	@RequestMapping(value="submit_order.do")
	public String submitOrder(HttpServletRequest request,HttpServletResponse response,Integer address_id,String sharr,Integer status) throws IOException {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<sharr:"+sharr);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<address_id:"+address_id);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		giftService.addOrder(sharr, userInfo.getAccount(), address_id, status);
		PrintWriter writer = response.getWriter();
		writer.println("成功");
		writer.close();
		return "";
	}
	
	@RequestMapping(value="submit_order_single.do")
	public ModelAndView submitOrderSingle(HttpServletRequest request, Integer address_id, String spec_ids, Integer num, Integer gift_id, Integer status) throws IOException{
		System.out.println(status);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		giftService.addSingleOrder(userInfo.getAccount(),address_id,spec_ids,num,gift_id,status);
		ModelAndView mView = null;
		if (status == 0) {
			mView = new ModelAndView("nopay");
		}else {
			mView = new ModelAndView("success");
		}
		return mView;
	}
	
}
