package com.soft.gift.mapper;

import com.soft.gift.model.LargeOrderInfo;
import com.soft.gift.model.OrderInfo;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoDAO extends BaseDAO<OrderInfo> {
	public List<LargeOrderInfo> getLargeOrderInfo(@Param("order_id") String order_id);
}
