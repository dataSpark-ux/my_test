package com.wy.newblog.thread.car;

/**
 * @author wy
 * @Description 轮子
 * @createTime 2019/03/24
 */
public class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.err.println(this + "installing Wheels");
        assembler.car().addWheels();
    }
}
