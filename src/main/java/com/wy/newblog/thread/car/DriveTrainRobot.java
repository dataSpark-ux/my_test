package com.wy.newblog.thread.car;

/**
 * @author wy
 * @Description 传动
 * @createTime 2019/03/24
 */
public class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.err.println(this + "installing DriveTrain");
        assembler.car().addDiveTrain();
    }
}
