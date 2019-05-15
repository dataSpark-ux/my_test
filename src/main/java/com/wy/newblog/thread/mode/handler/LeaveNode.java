package com.wy.newblog.thread.mode.handler;

import lombok.Data;

/**
 * @author wy
 * @Description 模拟请假条
 * @createTime 2019/04/11
 */
@Data
public class LeaveNode {
    /**
     * 请假天数
     */
    private int number;
    /**
    *请假人
    */
    private String person;

    public LeaveNode(int number, String person) {
        this.number = number;
        this.person = person;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
