package com.soft.gift.mapper;

import com.soft.gift.model.GiftSpec;
import com.soft.gift.model.Spec;
import com.soft.gift.model.SpecNum;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GiftSpecDAO extends BaseDAO<GiftSpec> {
	public List<Spec> getGiftSpec(@Param("gift_id") Integer gift_id, @Param("type") Integer type);
	public List<SpecNum> getSpecNum(@Param("gift_id") Integer gift_id);
}
