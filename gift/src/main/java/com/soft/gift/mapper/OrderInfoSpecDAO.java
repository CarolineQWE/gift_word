package com.soft.gift.mapper;

import com.soft.gift.model.OrderInfoSpec;
import com.soft.gift.model.Spec;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoSpecDAO extends BaseDAO<OrderInfoSpec> {
	public List<Spec> getSpecsByOrderInfoID(@Param("orderinfo_id") String orderinfo_id);
}
