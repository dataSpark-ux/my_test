package com.wy.newblog.controller;

import com.wy.newblog.base.BaseController;
import com.wy.newblog.core.Result;
import com.wy.newblog.entity.OrderEntity;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wy
 * @Date: 2018/10/23 23
 * @Description:
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController extends BaseController {
    @Resource
    private IOrderService orderService;

    @ApiOperation("基于redis的zset类型实现订单过期消失")
    @PostMapping("/redis")
    public Result sendOrderTest1(@RequestBody OrderEntity order) {
        return orderService.sendOrderRedisTest1(order);
    }
    @ApiOperation("监听订单")
    @PostMapping("/redis/listener")
    public void sendOrderTest3() {
        orderService.sendOrderRedisTest2();
    }
    @ApiOperation("消费订单")
    @PostMapping("/redis/consume/{orderId}")
    public Result sendOrderTest4(@PathVariable("orderId") Long orderId) {
        try {
            return orderService.sendOrderRedisTest3(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.UPDATE_FAILURE);
    }


    @ApiOperation("基于rabbitmq的延迟队列实现订单过期消失")
    @PostMapping("/rabbitmq")
    public Result sendOrderTest2(@RequestBody OrderEntity order) {
        return orderService.sendOrderRabbitmqTest2(order);
    }
    @ApiOperation("支付订单")
    @PostMapping("/pay/{orderId}")
    public Result payOrderTest1(@PathVariable("orderId") Long orderId) {
        return orderService.payOrderTest1(orderId);
    }

}
