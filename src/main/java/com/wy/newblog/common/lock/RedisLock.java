package com.wy.newblog.common.lock;

import com.wy.newblog.common.utils.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author
 * @Date 2018/11/26 10:50
 * @Description TODO
 * @Version 1.0
 */
@Component
public abstract class RedisLock implements Lock {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RedisTemplate redisTemplate;

    protected String lockKey;

    protected String lockValue;

    protected volatile boolean isOpenExpirationRenewal = true;


    public RedisLock() {
        redisTemplate = ApplicationContextProvider.getBean(RedisTemplate.class);
    }

    public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this(redisTemplate, lockKey, UUID.randomUUID().toString() + Thread.currentThread().getId());
    }


    public RedisLock(RedisTemplate redisTemplate, String lockKey, String lockValue) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
    }

    /**
     * 开启定时刷新
     */
    protected void scheduleExpirationRenewal() {
        Thread renewalThread = new Thread(new ExpirationRenewal());
        renewalThread.start();
    }


    public void sleepBySencond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private class ExpirationRenewal implements Runnable {

        @Override
        public void run() {
            while (isOpenExpirationRenewal) {
                logger.info("【执行延迟失效时间中。。。。。。。。】");
                String checkAndExpireScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else " +
                        "return 0 end";
                RedisScript<String> redisScript = new DefaultRedisScript<>(checkAndExpireScript, String.class);
                String execute = (String) redisTemplate.execute(redisScript, Collections.singletonList(lockKey), lockValue);
                logger.debug("execute===={}", execute);
                sleepBySencond(10);
            }
        }
    }
}
