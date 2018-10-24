package com.wy.newblog.controller;

import com.wy.newblog.base.BaseController;
import com.wy.newblog.core.Result;
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

    @ApiOperation("基于redis的zset类型")
    @GetMapping("/test")
    public void sendOrderTest1() {

        Result result = orderService.sendOrderTest1();

    }

}
