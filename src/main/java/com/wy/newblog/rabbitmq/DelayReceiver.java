package com.wy.newblog.rabbitmq;

import com.rabbitmq.client.Channel;
import com.wy.newblog.entity.Order;
import com.wy.newblog.entity.OrderEntity;
import com.wy.newblog.entity.enums.Status;
import com.wy.newblog.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @author 13002
 */
@Component
@Slf4j
public class DelayReceiver {
    @Resource
    private OrderRepository orderRepository;

    @RabbitListener(queues = {DelayRabbitConfig.ORDER_QUEUE_NAME})
    public void orderDelayQueue(Long orderId, Message message, Channel channel) {
        log.info("【订单Id】-[{}]-【message】-[{}]-【channel】-[{}]",orderId,message,channel);
        log.info("###########################################");
        log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]", LocalDateTime.now(), orderId);
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        log.info("【订单状态】-[{}]",order.get().getIsPay());
        OrderEntity orderEntity = order.get();
        if(orderEntity.getIsPay().code()== 0) {
            orderEntity.setStatus(Status.DELETED);
            OrderEntity or = orderRepository.save(orderEntity);
            log.info("【该订单未支付，取消订单】" + or.toString());
        } else if(orderEntity.getIsPay().code() == 1) {
            log.info("【该订单已完成支付】");
        } else if(orderEntity.getIsPay().code() == 2) {
            log.info("【该订单已取消】");
        }
        log.info("###########################################");
    }
}
