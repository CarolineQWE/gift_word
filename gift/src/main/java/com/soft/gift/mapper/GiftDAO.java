package com.soft.gift.mapper;

import com.soft.gift.model.Gift;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GiftDAO extends BaseDAO<Gift> {
	public List<Gift> getAllGiftNew();
	public List<Gift> getAllGiftHot();
	public List<Gift> selectByKeyWords(String keyword);
	public Integer insertGift(Gift gift);

	public Integer[] batchDeleteGift(@Param(value = "gift_ids") List<Integer> gift_ids);
}
