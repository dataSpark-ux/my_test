package com.wy.newblog.common.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @Date 2018/11/26 13:08
 * @Description redis分布式锁
 * @Version 1.0
 */
public class RedisCase extends RedisLock {

    public RedisCase(RedisTemplate redisTemplate, String lockKey) {
        super(redisTemplate, lockKey);
    }

    @Override
    public void lock() {
        while (true) {
            redisTemplate.opsForValue().set("bibi", "wdw");
            Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue);
            if (result) {
                Boolean expire = redisTemplate.expire(lockKey, 30, TimeUnit.SECONDS);
                if (expire) {
                    logger.info("【线程ID】:{}", Thread.currentThread().getId() + "【加锁成功！时间】:{}", LocalDateTime.now());

                    //开启定时刷新过期时间
                    isOpenExpirationRenewal = true;
                    scheduleExpirationRenewal();
                    break;
                }
                logger.info("【线程ID】:{}", Thread.currentThread().getId() + "【获取锁失败，休眠10秒！时间】:{}", LocalDateTime.now());
                sleepBySencond(10);
            }

        }
    }

    @Override
    public void unlock() {
        logger.info("【线程ID】:{}", Thread.currentThread().getId() + "【解锁时间】：{}", LocalDateTime.now());
        String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) " +
                "else " +
                "return 0 " +
                "end";
        RedisScript<String> DEL_SCRIPT = new DefaultRedisScript<>(checkAndDelScript, String.class);
        Object execute = redisTemplate.execute(DEL_SCRIPT, Collections.singletonList(lockKey), lockValue);
        logger.debug("execute======{}", execute);
        isOpenExpirationRenewal = false;
    }
}
