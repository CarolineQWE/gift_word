package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.*;
import com.soft.gift.model.*;
import com.soft.gift.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fyq on 2017/5/12.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderInfoDAO orderinfodao;
    @Autowired
    private OrderInfoSpecDAO orderInfoSpecDAO;
    @Autowired
    private SpecDAO specdao;
    @Autowired
    private ShippingAddressDAO shippingAddressDAO;

    @Override
    public Order getOrderById(String order_id) {
        Order order = orderDAO.selectByPrimaryKey(order_id);
        return order;
    }

    @Override
    public Map<LargeOrderInfo, List<Spec>> getOrderInfoByOrderId(String order_id) {
        List<LargeOrderInfo> orderInfos = orderinfodao.getLargeOrderInfo(order_id);
        Map<LargeOrderInfo,List<Spec>> map = new HashMap<>();
        for(LargeOrderInfo o:orderInfos){
            OrderInfoSpec orderInfoSpec = new OrderInfoSpec();
            orderInfoSpec.setOrderinfo_id(o.getOrderinfo_id());
            List<OrderInfoSpec> orderInfoSpecs = orderInfoSpecDAO.select(orderInfoSpec);
            List<Spec> specs = new ArrayList<>();
            for(OrderInfoSpec orderSpec:orderInfoSpecs){
                Spec spec = specdao.selectByPrimaryKey(orderSpec.getSpec_id());
                specs.add(spec);
            }
            map.put(o,specs);
        }
        return map;
    }

    @Override
    public ShippingAddress getAddressByAddressId(Integer address_id) {
        ShippingAddress address = shippingAddressDAO.selectByPrimaryKey(address_id);
        return address;
    }

    @Override
    public List<OrderInfo> getBriefOrderInfoByOrderId(String order_id) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_id(order_id);
        List<OrderInfo> orderInfos = orderinfodao.select(orderInfo);
        return orderInfos;
    }
}
