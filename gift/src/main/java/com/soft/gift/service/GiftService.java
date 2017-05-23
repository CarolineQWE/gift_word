package com.soft.gift.service;

import com.soft.gift.model.*;

import java.util.List;
import java.util.Map;

public interface GiftService {
	//礼物操作
	public Map<Category, List<Category>> getMallMenu();
	public List<Gift> getLastedGift();
	public List<Gift> getHotGift();
	public GiftInfo getGigtInfoByID(Integer gift_id);
	public Gift getGiftByID(Integer gift_id);
	public List<Spec> getGiftSpecByGiftIDAndType(Integer gift_id, Integer type);
	public List<SpecNum> getSpecNum(Integer gift_id);
	public Spec getSpecBySpecID(Integer spec_id);
	public List<Gift> searchGiftByCateID(Integer cate_id);
	public List<Category> getFirstCate();
	public Map<String, List<Spec>> getSpecBySpecName(int i);
	public List<Category> getSecondMenu(Integer id);
	public List<Gift> searchGiftByKeyword(String keyword);
	
	//购物车操作
	public void insertScSpec(ScSpec scSpec);
	public void addShoppingCart(ShoppingCart shoppingCart);
	public Map<LargeShopCart, List<Spec>> getShopCartAllInfo(String account);
	public void deleteSh(String sh_id);
	public void modifySh(String sh_id, Integer new_num);
	public List<ShippingAddress> getAddressByAccount(String account);
	public ShoppingCart getShopCartByID(String sh_id);
	public List<Spec> getShSpecByShID(String sh_id);
	
	//订单操作
	public void addOrder(String sharr, String account, Integer address_id, Integer status);
	public void addSingleOrder(String account, Integer address_id, String spec_ids, Integer num, Integer gift_id, Integer status);
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByAccount(String account);
	public void modifyOrderStatus(String order_id, Integer newStatus);
	public void deleteOrderByOrderID(String order_id);
	public void modifyGiftStockByOrderId(String order_id);
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByAccountAndStatus(String account, Integer status);
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByStatus(Integer status);
	public Map<LargeOrder, Map<LargeOrderInfo, List<Spec>>> getAllOrder();

	//新增礼物
    public Integer addGift(Gift gift);

	public void addGiftInfo(GiftInfo giftInfo);

	public void addGiftSpec(GiftSpec giftSpec);

    public Category getCateByID(Integer category_id);

	public void batchDeleteGift(String str);

	public void deleteGift(Integer gift_id);

	public void updateGiftStatus(Integer gift_id);

	public void updateGiftById(Gift gift);

	public void updateGiftInfoByGiftId(GiftInfo giftInfo);

    public List<List<List<Gift>>> getAllGifts();

	public void addSaleSpec(Spec spec);

	public void addBaseSpec(Spec spec);

    public Order getOrderByID(String order_id);

 	public void updateGift(Gift gift);

	//搜索礼物
	public List<Gift> searchGiftByCateIDOrderBySale(Integer cate_id);

	public List<Gift> searchGiftByCateIDOrderByHot(Integer cate_id);

	public List<Gift> searchGiftByCateIDOrderByPrice(Integer cate_id, Integer min, Integer max);

	public List<Gift> searchGiftByLargeCate(Integer large_cate);

	public List<Gift> searchGiftByLargeCateOrderBySale(Integer large_cate);

	public List<Gift> searchGiftByLargeCateOrderByHot(Integer large_cate);

	public List<Gift> searchGiftByLargeCateOrderByPrice(Integer large_cate, Integer min, Integer max);

	public Category getCategory(Category category);

    public List<Gift> getCustomGift(Integer bool);
}
