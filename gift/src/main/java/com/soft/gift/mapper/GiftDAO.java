package com.soft.gift.mapper;

import com.soft.gift.model.Gift;
import com.soft.gift.util.BaseDAO;

import java.util.List;

public interface GiftDAO extends BaseDAO<Gift> {
	public List<Gift> getAllGiftNew();
	public List<Gift> getAllGiftHot();
	public List<Gift> selectByKeyWords(String keyword);
	public int insertGift(Gift gift);
}
