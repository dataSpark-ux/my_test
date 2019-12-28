package com.wy.newblog.thread.mode.handler;

/**
 * @author wy
 * @Description 系主任
 * @createTime 2019/04/11
 */
public class DepartmentHead extends AbstractLeader {

    public DepartmentHead(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        //小于7天系主任审批
        if (leaveNode.getNumber() <= 7) {
            System.out.println("系主任" + name + "审批" + leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {     //否则传递给院长
            if (this.successor != null) {
                this.successor.handleRequest(leaveNode);
            }
        }
    }
}
