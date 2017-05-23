package com.soft.gift.service;

import com.soft.gift.model.*;

import java.util.List;
import java.util.Map;

public interface OrderService {

 	public Order getOrderById(String order_id);

	public Map<LargeOrderInfo,List<Spec>> getOrderInfoByOrderId(String order_id);

	public ShippingAddress getAddressByAddressId(Integer address_id);

    List<OrderInfo> getBriefOrderInfoByOrderId(String order_id);
}
