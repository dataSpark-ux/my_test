package com.wy.newblog.thread.mode.handler;

/**
 * @author wy
 * @Description 辅导员
 * @createTime 2019/04/11
 */
public class Instructor extends AbstractLeader {


    public Instructor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if (leaveNode.getNumber() <= 3) {
            System.out.println("辅导员" + name + "审批" + leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {
            //否则传给系主任
            if (this.successor != null) {
                this.successor.handleRequest(leaveNode);
            }
        }
    }
}
