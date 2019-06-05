package com.wy.newblog.controller;

import com.wy.newblog.LiftOff;
import com.wy.newblog.common.Result;
import com.wy.newblog.service.IThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

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
    @Resource(name = "consumerQueueThreadPool")
    private ExecutorService consumerQueueThreadPool;

    @ApiOperation("开启多线程访问是否数据一致")
    @GetMapping("/thread/test")
    public void threadTest() {
        Result result = threadService.threadOrderTest();
    }

    @ApiOperation("线程池的创建与使用")
    @GetMapping
    public void test() {
        for (int i = 0; i < 5; i++) {
            consumerQueueThreadPool.execute(new LiftOff());
        }
    }
    @GetMapping("/countDownLatch")
    public Result countDownLatchTest() {
        return threadService.countDownLatch();
    }

    @GetMapping("/countDown")
    public Result countDown() {
        return threadService.pageAll();
    }
}
