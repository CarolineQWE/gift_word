package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.*;
import com.soft.gift.model.*;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GiftServiceImpl implements GiftService {
	@Autowired
	private GiftSpecDAO giftSpecDAO;
	@Autowired
	private GiftDAO giftDAO;
	@Autowired
	private GiftInfoDAO giftInfoDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private SpecDAO specDAO;
	@Autowired
	private ScSpecDAO scSpecDAO;
	@Autowired
	private ShoppingCartDAO shoppingCartDAO;
	@Autowired
	private ShippingAddressDAO shippingAddressDAO;
	@Autowired
	private OrderInfoSpecDAO orderInfoSpecDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private OrderInfoDAO orderInfoDAO;

	@Override
	public Map<Category, List<Category>> getMallMenu() {
		Map<Category,List<Category>> map = new HashMap<Category,List<Category>>();
		List<Category> firstList = categoryDAO.getMenu(0);
		List<Category> secondList = new ArrayList<Category>();
		for (Category spec : firstList) {
			secondList = categoryDAO.getMenu(spec.getId());
			map.put(spec, secondList);
		}
		return map;
	}

	@Override
	public List<Gift> getLastedGift() {
		List<Gift> gifts = new ArrayList<Gift>();
		gifts = giftDAO.getAllGiftNew();
		return gifts;
	}

	@Override
	public List<Gift> getHotGift() {
		List<Gift> gifts = new ArrayList<Gift>();
		gifts = giftDAO.getAllGiftHot();
		return gifts;
	}

	@Override
	public GiftInfo getGigtInfoByID(Integer gift_id) {
		GiftInfo giftInfo = new GiftInfo();
		giftInfo.setGift_id(gift_id);
		giftInfo = giftInfoDAO.selectOne(giftInfo);
		return giftInfo;
	}

	@Override
	public Gift getGiftByID(Integer gift_id) {
		return giftDAO.selectByPrimaryKey(gift_id);
	}

	@Override
	public List<Spec> getGiftSpecByGiftIDAndType(Integer gift_id, Integer type) {
		List<Spec> specs = new ArrayList<Spec>();
		specs = giftSpecDAO.getGiftSpec(gift_id,type);
		return specs;
	}

	@Override
	public List<SpecNum> getSpecNum(Integer gift_id) {
		List<SpecNum> list = new ArrayList<SpecNum>();
		list = giftSpecDAO.getSpecNum(gift_id);
		return list;
	}

	@Override
	public Spec getSpecBySpecID(Integer spec_id) {
		return specDAO.selectByPrimaryKey(spec_id);
	}

	@Override
	public void insertScSpec(ScSpec scSpec) {
		scSpecDAO.insert(scSpec);
	}

	@Override
	public void addShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartDAO.insert(shoppingCart);
	}

	@Override
	public Map<LargeShopCart, List<Spec>> getShopCartAllInfo(String account) {
		Map<LargeShopCart, List<Spec>> map = new HashMap<LargeShopCart, List<Spec>>();
		List<Spec> specs = null;
		List<LargeShopCart> largeShopCarts = shoppingCartDAO.getLargeShopCart(account);
		for (LargeShopCart largeShopCart : largeShopCarts) {
			specs = scSpecDAO.getSpecByShID(largeShopCart.getSc_id());
			map.put(largeShopCart, specs);
		}
		return map;
	}

	@Override
	public void deleteSh(String sh_id) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(sh_id);
		shoppingCartDAO.delete(shoppingCart);
	}

	@Override
	public void modifySh(String sh_id, Integer new_num) {
		shoppingCartDAO.updateSh(sh_id,new_num);
	}

	@Override
	public List<ShippingAddress> getAddressByAccount(String account) {
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setAccount(account);
		List<ShippingAddress> addresses = new ArrayList<ShippingAddress>();
		addresses = shippingAddressDAO.select(shippingAddress);
		return addresses;
	}

	@Override
	public ShoppingCart getShopCartByID(String sh_id) {
		ShoppingCart sh = shoppingCartDAO.selectByPrimaryKey(sh_id);
		return sh;
	}

	@Override
	public List<Spec> getShSpecByShID(String sh_id) {
		List<Spec> specs = new ArrayList<Spec>();
		specs = scSpecDAO.getSpecByShID(sh_id);
		return specs;
	}

	@Override
	public void addOrder(String sharr,String account,Integer address_id,Integer status) {
		String [] sh_ids = sharr.split(",");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = simpleDateFormat.format(timestamp);
		String order_id = time+account;
		Double total_price = 0.0;
		Integer info_num = 0;
		for (String sh_id : sh_ids) {
			ShoppingCart sh = getShopCartByID(sh_id);
			total_price += sh.getPrice();
			info_num+=1;
		}
		Order order = new Order(order_id,account,new Timestamp(new java.util.Date().getTime()),status,address_id,total_price,0,info_num);
		orderDAO.insert(order);
		List<ShoppingCart> shs = new ArrayList<ShoppingCart>();
		for (String sh_id : sh_ids) {
			ShoppingCart sh = getShopCartByID(sh_id);
			shs.add(sh);
			ScSpec scSpec = new ScSpec();
			scSpec.setSc_id(sh_id);
			List<ScSpec> scSpecs = scSpecDAO.select(scSpec);
			OrderInfo orderInfo = new OrderInfo(sh_id, order_id, sh.getNum(), sh.getGift_id(), sh.getPrice());
			orderInfoDAO.insert(orderInfo);
			for (ScSpec scSpec2 : scSpecs) {
				OrderInfoSpec orderInfoSpec = new OrderInfoSpec(sh_id, scSpec2.getSpec_id());
				orderInfoSpecDAO.insert(orderInfoSpec);
				scSpecDAO.delete(scSpec);
			}
			shoppingCartDAO.delete(sh);
		}
		if (status == 1) {
			for (ShoppingCart sh : shs) {
				Gift gift = giftDAO.selectByPrimaryKey(sh.getGift_id());
				gift.setSale_num(gift.getSale_num()+1);
				giftDAO.updateByPrimaryKey(gift);
			}
		}

	}

	@Override
	public void addSingleOrder(String account, Integer address_id, String spec_ids, Integer num, Integer gift_id,Integer status) {
		String [] specIds = spec_ids.split(",");
		Gift gift = giftDAO.selectByPrimaryKey(gift_id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = simpleDateFormat.format(timestamp);
		String order_id = time+account;
		Order order = new Order(order_id,account,new Timestamp(new java.util.Date().getTime()),status,address_id,gift.getPrice()*num,0,1);
		orderDAO.insert(order);
		String orderinfo_id = time+account+gift_id;
		OrderInfo orderInfo = new OrderInfo(orderinfo_id, order_id, num, gift_id, gift.getPrice());
		orderInfoDAO.insert(orderInfo);
		for (String string : specIds) {
			OrderInfoSpec orderInfoSpec = new OrderInfoSpec(orderinfo_id, Integer.parseInt(string));
			orderInfoSpecDAO.insert(orderInfoSpec);
		}
	}

	@Override
	public Map<Order, Map<LargeOrderInfo,List<Spec>>> getOrderByAccount(String account) {
		Map<Order, Map<LargeOrderInfo,List<Spec>>> map = new HashMap<Order, Map<LargeOrderInfo,List<Spec>>>();
		Map<LargeOrderInfo, List<Spec>> innerMap = null;
		List<Order> orders = new ArrayList<Order>();
		Order order2 = new Order();
		order2.setAccount(account);
		orders = orderDAO.select(order2);
		List<LargeOrderInfo> largeOrderInfos = new ArrayList<LargeOrderInfo>();
		List<Spec> specs = new ArrayList<Spec>();
		for (Order order : orders) {
			largeOrderInfos = orderInfoDAO.getLargeOrderInfo(order.getId());
			innerMap = new HashMap<LargeOrderInfo,List<Spec>>();
			for (LargeOrderInfo largeOrderInfo : largeOrderInfos) {
				specs = orderInfoSpecDAO.getSpecsByOrderInfoID(largeOrderInfo.getOrderinfo_id());
				innerMap.put(largeOrderInfo, specs);
			}
			map.put(order, innerMap);
		}
		return map;
	}

	@Override
	public void modifyOrderStatus(String order_id, Integer newStatus) {
		Order order = orderDAO.selectByPrimaryKey(order_id);
		order.setStatus(newStatus);
		System.out.println("newStatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+newStatus);
		orderDAO.updateByPrimaryKey(order);
	}

	@Override
	public void deleteOrderByOrderID(String order_id) {
		Order order = orderDAO.selectByPrimaryKey(order_id);
		orderDAO.deleteByPrimaryKey(order);
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrder_id(order_id);
		orderInfoDAO.delete(orderInfo);
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		orderInfos= orderInfoDAO.select(orderInfo);
		for (OrderInfo orderInfo2 : orderInfos) {
			OrderInfoSpec orderInfoSpec = new OrderInfoSpec();
			orderInfoSpec.setOrderinfo_id(orderInfo2.getId());
			orderInfoSpecDAO.delete(orderInfoSpec);
		}

	}

	@Override
	public void modifyGiftStockByOrderId(String order_id) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrder_id(order_id);
		orderInfos = orderInfoDAO.select(orderInfo);
		for (OrderInfo orderInfo2 : orderInfos) {
			Gift gift = giftDAO.selectByPrimaryKey(orderInfo2.getGift_id());
			gift.setStock(gift.getStock()+1);
			giftDAO.updateByPrimaryKey(gift);
		}
	}

	@Override
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByAccountAndStatus(String account, Integer status) {
		Map<Order, Map<LargeOrderInfo,List<Spec>>> map = new HashMap<Order, Map<LargeOrderInfo,List<Spec>>>();
		Map<LargeOrderInfo, List<Spec>> innerMap = null;
		List<Order> orders = new ArrayList<Order>();
		Order order2 = new Order();
		order2.setAccount(account);
		order2.setStatus(status);
		orders = orderDAO.select(order2);
		List<LargeOrderInfo> largeOrderInfos = new ArrayList<LargeOrderInfo>();
		List<Spec> specs = new ArrayList<Spec>();
		for (Order order : orders) {
			largeOrderInfos = orderInfoDAO.getLargeOrderInfo(order.getId());
			innerMap = new HashMap<LargeOrderInfo,List<Spec>>();
			for (LargeOrderInfo largeOrderInfo : largeOrderInfos) {
				specs = orderInfoSpecDAO.getSpecsByOrderInfoID(largeOrderInfo.getOrderinfo_id());
				innerMap.put(largeOrderInfo, specs);
			}
			map.put(order, innerMap);
		}
		return map;
	}

	@Override
	public Map<Order, Map<LargeOrderInfo, List<Spec>>> getOrderByStatus(Integer status) {
		Map<Order, Map<LargeOrderInfo,List<Spec>>> map = new HashMap<Order, Map<LargeOrderInfo,List<Spec>>>();
		Map<LargeOrderInfo, List<Spec>> innerMap = null;
		List<Order> orders = new ArrayList<Order>();
		Order order2 = new Order();
		order2.setStatus(status);
		orders = orderDAO.select(order2);
		List<LargeOrderInfo> largeOrderInfos = new ArrayList<LargeOrderInfo>();
		List<Spec> specs = new ArrayList<Spec>();
		for (Order order : orders) {
			largeOrderInfos = orderInfoDAO.getLargeOrderInfo(order.getId());
			innerMap = new HashMap<LargeOrderInfo,List<Spec>>();
			for (LargeOrderInfo largeOrderInfo : largeOrderInfos) {
				specs = orderInfoSpecDAO.getSpecsByOrderInfoID(largeOrderInfo.getOrderinfo_id());
				innerMap.put(largeOrderInfo, specs);
			}
			map.put(order, innerMap);
		}
		return map;
	}

	@Override
	public List<Gift> searchGiftByCateID(Integer cate_id) {
		Gift gift = new Gift();
		gift.setCategory(cate_id);
		List<Gift> gifts = new ArrayList<Gift>();
		gifts = giftDAO.select(gift);
		return gifts;
	}

	@Override
	public List<Gift> searchGiftByKeyword(String keyword) {
		List<Gift> gifts = new ArrayList<Gift>();
		gifts = giftDAO.selectByKeyWords(keyword);
		return gifts;
	}

	@Override
	public Map<LargeOrder, Map<LargeOrderInfo, List<Spec>>> getAllOrder() {
		Map<LargeOrder, Map<LargeOrderInfo,List<Spec>>> map = new HashMap<LargeOrder, Map<LargeOrderInfo,List<Spec>>>();
		Map<LargeOrderInfo, List<Spec>> innerMap = null;
		List<Order> orders = new ArrayList<Order>();
		orders = orderDAO.selectAll();
		List<LargeOrderInfo> largeOrderInfos = new ArrayList<LargeOrderInfo>();
		List<Spec> specs = new ArrayList<Spec>();
		for (Order order : orders) {
			ShippingAddress address= shippingAddressDAO.selectByPrimaryKey(order.getAddress_id());
			LargeOrder order2 = new LargeOrder(order.getAccount(),address.getProv() , address.getCity(),
					address.getDist() ,address.getReceiver(), address.getTel(),
					order.getId(), order.getOrder_time(),order.getStatus());
			largeOrderInfos = orderInfoDAO.getLargeOrderInfo(order.getId());
			innerMap = new HashMap<LargeOrderInfo,List<Spec>>();
			for (LargeOrderInfo largeOrderInfo : largeOrderInfos) {
				specs = orderInfoSpecDAO.getSpecsByOrderInfoID(largeOrderInfo.getOrderinfo_id());
				innerMap.put(largeOrderInfo, specs);
			}
			map.put(order2, innerMap);
		}
		return map;
	}

	@Override
	public List<Category> getFirstCate() {
		List<Category> list = new ArrayList<Category>();
		Category category = new Category();
		category.setLeval(0);
		list = categoryDAO.select(category);
		return list;
	}

	@Override
	public Map<String, List<Spec>> getSpecBySpecName(int i) {
		List<String> specNames = specDAO.getSpecNames(i);
		Map<String, List<Spec>> map = new HashMap<String, List<Spec>>();
		for (String string : specNames) {
			Spec spec = new Spec();
			spec.setName(string);
			List<Spec> specs = specDAO.select(spec);
			map.put(string, specs);
		}
		return map;
	}

	@Override
	public List<Category> getSecondMenu(Integer id) {
		Category category = new Category();
		category.setP_id(id);
		List<Category> categories = categoryDAO.select(category);
		return categories;
	}



}
