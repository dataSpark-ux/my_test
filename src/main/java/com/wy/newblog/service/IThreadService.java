package com.wy.newblog.service;

import com.wy.newblog.common.Result;

/**
 * @author wy
 * @Date: 2018/10/24 21
 * @Description:
 */
public interface IThreadService {

    /**
     * @return
     */
    Result threadOrderTest();

    Result countDownLatch();

    Result pageAll();

}
