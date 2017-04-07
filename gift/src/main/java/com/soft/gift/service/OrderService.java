package com.soft.gift.service;

import com.soft.gift.model.LargeOrderInfo;
import com.soft.gift.model.Order;
import com.soft.gift.model.Spec;

import java.util.List;
import java.util.Map;

public interface OrderService {
	public void addOrder(String sharr, String account, Integer address_id, Integer status);
	public void addSingleOrder(String account, Integer address_id, String spec_ids, Integer num, Integer gift_id, Integer status);
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByAccount(String account);
	public void modifyOrderStatus(String order_id, Integer newStatus);
	public void deleteOrderByOrderID(String order_id);
	public void modifyGiftStockByOrderId(String order_id);
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByAccountAndStatus(String account, Integer status);
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByStatus(Integer status);
}
