package com.wy.newblog.thread.mode.handler;

/**
 * @author wy
 * @Description 抽象处理者
 * @createTime 2019/04/11
 */
public abstract class AbstractLeader {

    /**
     * 姓名
     */
    public String name;
    /**
     * 后继者
     */
    protected AbstractLeader successor;

    public AbstractLeader(String name) {
        this.name = name;
    }

    public void setSuccessor(AbstractLeader successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(LeaveNode leaveNode);
}
