package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.GiftService;
import com.soft.gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MyOrderController {
	@Autowired
	private GiftService giftService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/myOrder")
	public String myOrder(HttpServletRequest request, ModelMap mv){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByAccount(userInfo.getAccount());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>orderMap:"+orderMap);
		User user = userService.getUser(userInfo.getAccount());
		mv.put("identity",user.getIdentity());
		mv.put("status","5");
		mv.put("orderMap",orderMap);
		return "myOrder";
		
	}
	
	@RequestMapping(value="/order")
	public ModelAndView order(HttpServletRequest request){
		Map<LargeOrder, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getAllOrder();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>orderMap:"+orderMap);
		ModelAndView mv = new ModelAndView("order_admin");
		mv.addObject("identity",0);
		mv.addObject("status","5");
		mv.addObject("orderMap",orderMap);
		return mv;
		
	}
	
	@RequestMapping(value="/getOrdersByStatus")
	public ModelAndView getOrdersByStatus(HttpServletRequest request, Integer status){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByAccountAndStatus(userInfo.getAccount(),status);
		ModelAndView mv = new ModelAndView("myOrder");
		User user = userService.getUser(userInfo.getAccount());
		mv.addObject("identity",user.getIdentity());
		mv.addObject("orderMap",orderMap);
		mv.addObject("status",status);
		return mv;
	}
	
	
	@RequestMapping(value="/getAllOrdersByStatus")
	public ModelAndView getAllOrdersByStatus(HttpServletRequest request, Integer status){
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByStatus(status);
		ModelAndView mv = new ModelAndView("order_admin");
		mv.addObject("identity",0);
		mv.addObject("orderMap",orderMap);
		mv.addObject("status",status);
		return mv;
	}
	
	@RequestMapping(value="/modify_order.do")
	public String modifyOrder(HttpServletRequest request,String order_id,Integer newStatus){
		giftService.modifyOrderStatus(order_id, newStatus);
		return "redirect:myOrder.do";
	}
	
	
	@RequestMapping(value="/deleteOrder.do")
	public String deleteOrder(HttpServletRequest request,String order_id,Integer newStatus){
		giftService.deleteOrderByOrderID(order_id);
		return "redirect:myOrder.do";
	}
	
	@RequestMapping(value="/delivery.do")
	public String delivery(HttpServletRequest request,String order_id){
		giftService.modifyGiftStockByOrderId(order_id);
		giftService.modifyOrderStatus(order_id, 2);
		return "redirect:order.do";
	}
}
