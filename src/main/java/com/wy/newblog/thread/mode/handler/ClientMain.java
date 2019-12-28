package com.wy.newblog.thread.mode.handler;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/11
 */
public class ClientMain {
    public static void main(String[] args) {

        AbstractLeader instructor = new Instructor("辅导员");

        AbstractLeader departmentHead = new DepartmentHead("系主任");

        AbstractLeader dean = new Dean("院长");

        AbstractLeader president = new President("校长");

        instructor.setSuccessor(departmentHead);
        departmentHead.setSuccessor(dean);
        dean.setSuccessor(president);

        LeaveNode leaveNode = new LeaveNode(9, "张三");
        instructor.handleRequest(leaveNode);


    }
}
