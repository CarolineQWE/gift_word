package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyOrderController {
	@Autowired
	private GiftService giftService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DesignService designService;
	
	@RequestMapping(value="/myOrder")
	public String myOrder(HttpServletRequest request, ModelMap mv){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByAccount(userInfo.getAccount());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>orderMap:"+orderMap);
		User user = userService.getUser(userInfo.getAccount());
		mv.put("identity",user.getIdentity());
		mv.put("status","5");
		mv.put("mapSize",orderMap.size());
		mv.put("orderMap",orderMap);
		return "myOrder";
		
	}
	
	@RequestMapping(value="/order")
	public String  order(HttpServletRequest request,ModelMap mv){
		Map<LargeOrder, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getAllOrder();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>orderMap:"+orderMap);
		mv.put("status","5");
		mv.put("orderMap",orderMap);
		mv.put("mapSize",orderMap.size());
		return "order_admin";
		
	}
	
	@RequestMapping(value="/getOrdersByStatus")
	public String getOrdersByStatus(HttpServletRequest request, Integer status,ModelMap mv){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByAccountAndStatus(userInfo.getAccount(),status);
		mv.put("orderMap",orderMap);
		mv.put("mapSize",orderMap.size());
		mv.put("status",status);
		return "myOrder";
	}
	
	
	@RequestMapping(value="/getAllOrdersByStatus")
	public String getAllOrdersByStatus(HttpServletRequest request, Integer status,ModelMap mv){
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByStatus(status);
		mv.put("orderMap",orderMap);
		mv.put("mapSize",orderMap.size());
		mv.put("status",status);
		return "order_admin";
	}
	
	@RequestMapping(value="/modify_order")
	public String modifyOrder(HttpServletRequest request,String order_id,ModelMap mv){
		Order order = giftService.getOrderByID(order_id);
		giftService.modifyOrderStatus(order_id, order.getStatus()+1);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByAccount(userInfo.getAccount());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>orderMap:"+orderMap);
		mv.put("status","5");
		mv.put("orderMap",orderMap);
		mv.put("mapSize",orderMap.size());
		return "myOrder";
	}
	
	
	@RequestMapping(value="/deleteOrder")
	public String deleteOrder(HttpServletRequest request,String order_id,ModelMap mv){
		giftService.deleteOrderByOrderID(order_id);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<Order, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getOrderByAccount(userInfo.getAccount());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>orderMap:"+orderMap);
		mv.put("status","5");
		mv.put("orderMap",orderMap);
		mv.put("mapSize",orderMap.size());
		return "myOrder";
	}

	@RequestMapping(value="/delivery")
	public String delivery(HttpServletRequest request,String order_id,ModelMap mv){
		Order order = giftService.getOrderByID(order_id);
		giftService.modifyOrderStatus(order_id, order.getStatus()+1);
		Map<LargeOrder, Map<LargeOrderInfo, List<Spec>>> orderMap = giftService.getAllOrder();
		mv.put("orderMap",orderMap);
		mv.put("mapSize",orderMap.size());
		mv.put("status","5");
		return "order_admin";
	}

	@RequestMapping(value="/gift/giftBriefInfo")
	public String comment(HttpServletRequest request,Integer gift_id,ModelMap mv){
		Gift gift = giftService.getGiftByID(gift_id);
		System.out.print("gift_id"+gift_id);
		GiftInfo giftInfo = giftService.getGigtInfoByID(gift_id);
		List<Comment> comments = commentService.getCommentByGiftID(gift_id);
		Double avg = commentService.getAverageSorceByGiftID(gift_id);
		List<Integer> nums = commentService.getCommentNum(gift_id);
		mv.put("gift",gift);
		mv.put("giftInfo",giftInfo);
		mv.put("comments",comments);
		mv.put("avg",avg);
		mv.put("scoreNums",nums);
		return "giftBriefInfo";
	}

	@RequestMapping(value="/user/view_info")
	public String orderInfo(HttpServletRequest request,String order_id,ModelMap mv){
		Order order = orderService.getOrderById(order_id);
		Map<LargeOrderInfo,List<Spec>> map = orderService.getOrderInfoByOrderId(order_id);
		List<OrderInfo> orderInfos = orderService.getBriefOrderInfoByOrderId(order_id);
		Map<Design,Gift> designMap = new HashMap<>();
		for (OrderInfo o:orderInfos){
			Gift gift = giftService.getGiftByID(o.getGift_id());
			if (gift.getIf_custom_made() == 0){
				Design design = designService.getDesignByGiftIdAndAccount(gift.getId(),order.getAccount());
				designMap.put(design,gift);
			}
		}
		ShippingAddress address = orderService.getAddressByAddressId(order.getAddress_id());
		mv.put("designMap",designMap);
		mv.put("order",order);
		mv.put("infoMap",map);
		mv.put("address",address);
		return "order_info";
	}

	@RequestMapping(value="/admin/view_info")
	public String orderInfoAdmin(HttpServletRequest request,String order_id,ModelMap mv){
		Order order = orderService.getOrderById(order_id);
		Map<LargeOrderInfo,List<Spec>> map = orderService.getOrderInfoByOrderId(order_id);
		List<OrderInfo> orderInfos = orderService.getBriefOrderInfoByOrderId(order_id);
		Map<Design,Gift> designMap = new HashMap<>();
		for (OrderInfo o:orderInfos){
			Gift gift = giftService.getGiftByID(o.getGift_id());
			if (gift.getIf_custom_made() == 0){
				Design design = designService.getDesignByGiftIdAndAccount(gift.getId(),order.getAccount());
				designMap.put(design,gift);
			}
		}
		ShippingAddress address = orderService.getAddressByAddressId(order.getAddress_id());
		mv.put("designMap",designMap);
		mv.put("order",order);
		mv.put("infoMap",map);
		mv.put("address",address);
		return "order_info_admin";
	}

}
