package com.soft.gift.mapper;

import com.soft.gift.model.Gift;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GiftDAO extends BaseDAO<Gift> {
	public List<Gift> getAllGiftNew();
	public List<Gift> getAllGiftHot();

	public Integer insertGift(Gift gift);

	public Integer[] batchDeleteGift(@Param(value = "gift_ids") List<Integer> gift_ids);

	public List<Gift> getOneCateGiftOrderByTime(@Param(value = "cate") Integer cate);

	public List<Gift> getOneCateGiftOrderByHot(@Param(value = "cate") Integer cate);

	//搜索
	public List<Gift> selectByKeyWords(@Param(value = "keyword") String keyword);

	public List<Gift> searchGiftByCateID(@Param(value = "cate_id") Integer cate_id);

	public List<Gift> searchGiftByCateIDOrderBySale(@Param(value = "cate_id") Integer cate_id);

	public List<Gift> searchGiftByCateIDOrderByHot(@Param(value = "cate_id") Integer cate_id);

	public List<Gift> searchGiftByCateIDOrderByPrice(@Param(value = "cate_id") Integer cate_id,@Param(value = "min") Integer min, @Param(value = "max") Integer max);

	public List<Gift> searchGiftByLargeCate(@Param(value = "large_cate") Integer large_cate);

	public List<Gift> searchGiftByLargeCateOrderBySale(@Param(value = "large_cate") Integer large_cate);

	public List<Gift> searchGiftByLargeCateOrderByHot(@Param(value = "large_cate") Integer large_cate);

	public List<Gift> searchGiftByLargeCateOrderByPrice(@Param(value = "large_cate") Integer large_cate, @Param(value = "min") Integer min, @Param(value = "max") Integer max);
}
