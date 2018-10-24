package com.wy.newblog.controller;

import com.wy.newblog.core.Result;
import com.wy.newblog.service.IThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wy
 * @Date: 2018/10/24 21
 * @Description:
 */
@RestController
@RequestMapping("/order")
@Api(tags = "并发测试类")
public class ThreadController {
    @Resource
    private IThreadService threadService;


    @ApiOperation("开启多线程访问是否数据一致")
    @GetMapping("/thread/test")
    public void threadTest() {
        Result result = threadService.threadOrderTest();
    }
}
