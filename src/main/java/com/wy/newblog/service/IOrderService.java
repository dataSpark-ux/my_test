package com.wy.newblog.service;

import com.wy.newblog.core.Result;
import com.wy.newblog.entity.OrderEntity;

/**
 * @author wy
 * @Date: 2018/10/23 23
 * @Description:
 */
public interface IOrderService {

    /**
     * 基于redis实现订单超时
     * @return
     */
    Result sendOrderTest1();

    /**
     * 基于rabbitma实现订单超时
     * @param order
     * @return
     */
    Result sendOrderTest2(OrderEntity order);

    /**
     * 支付订单
     * @param order
     * @return
     */
    Result payOrderTest1(Long orderId);
}
