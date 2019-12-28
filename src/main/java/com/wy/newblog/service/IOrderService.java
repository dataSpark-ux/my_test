package com.wy.newblog.service;

import com.wy.newblog.common.Result;
import com.wy.newblog.entity.OrderEntity;

/**
 * @author wy
 * @Date: 2018/10/23 23
 * @Description:
 */
public interface IOrderService {

    /**
     * 基于redis实现订单超时
     *
     * @param order
     * @return
     */
    Result sendOrderRedisTest1(OrderEntity order);

    /**
     * 消费消息
     *
     * @return
     */
    void sendOrderRedisTest2();

    /**
     * 基于rabbitma实现订单超时
     *
     * @param order
     * @return
     */
    Result sendOrderRabbitmqTest2(OrderEntity order);

    /**
     * 支付订单hahah
     *
     * @param orderId
     * @return Result
     */
    Result payOrderTest1(Long orderId);

    /**
     * redis消费订单
     *
     * @param orderId
     * @return Result
     */
    Result sendOrderRedisTest3(Long orderId) throws Exception;

    /**
     * @return
     */
    Result lockRedisTest();
}
