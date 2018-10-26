package com.wy.newblog.controller;

import com.wy.newblog.base.BaseController;
import com.wy.newblog.core.Result;
import com.wy.newblog.entity.Order;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.rabbitmq.DelaySender;
import com.wy.newblog.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @suther: wy
 * @Date: 2018/10/23 23
 * @Description:
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController extends BaseController {
    @Resource
    private IOrderService orderService;
    @Resource
    private DelaySender delaySender;
    @ApiOperation("基于redis的zset类型")
    @GetMapping("/test")
    public void sendOrderTest1() {

        Result result = orderService.sendOrderTest1();
    }
    @ApiOperation("基于rabbitmq的延迟队列实现订单过期消失")
    @GetMapping("/rabbitmq")
    public Result sendOrderTest2() {
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("123456");
        order1.setOrderName("小米6");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("456789");
        order2.setOrderName("小米8");
        delaySender.sendDelay(order1);
        delaySender.sendDelay(order2);
        return new Result(ResultCode.OK);
    }
}
