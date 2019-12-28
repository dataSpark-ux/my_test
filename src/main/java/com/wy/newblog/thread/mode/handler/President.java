package com.wy.newblog.thread.mode.handler;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/11
 */
public class President extends AbstractLeader {
    public President(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        //小于15天校长长审批
        if (leaveNode.getNumber() <= 15) {
            System.out.println("校长" + name + "审批" + leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {     //否则不允批准
            System.out.println("请假天天超过15天,不批准...");
        }
    }
}
