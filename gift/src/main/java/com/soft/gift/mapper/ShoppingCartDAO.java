package com.soft.gift.mapper;

import com.soft.gift.model.LargeShopCart;
import com.soft.gift.model.ShoppingCart;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartDAO extends BaseDAO<ShoppingCart> {
	public List<LargeShopCart> getLargeShopCart(@Param("account") String account);

	public void updateSh(@Param("id") String id, @Param("num") Integer num);
}
