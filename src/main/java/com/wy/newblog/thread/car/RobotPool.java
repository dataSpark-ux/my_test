package com.wy.newblog.thread.car;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wy
 * @Description 专家池
 * @createTime 2019/03/24
 */
public class RobotPool {

    private Set<Robot> pool = new HashSet<>();

    public synchronized void add(Robot robot) {
        pool.add(robot);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Robot> robotType, Assembler assembler) throws InterruptedException {
        for (Robot r : pool) {
            if (r.getClass().equals(robotType)) {
                System.out.println(r);
                pool.remove(r);
                r.assignAssembler(assembler);
                // 加强它完成任务
                r.enage();
                return;
            }
            // 没有
            wait();
            // 再次尝试递归
            hire(robotType, assembler);
        }
    }

    public synchronized void release(Robot robot) {
        add(robot);
    }
}
