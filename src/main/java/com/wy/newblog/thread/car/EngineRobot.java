package com.wy.newblog.thread.car;

/**
 * @author wy
 * @Description 引擎
 * @createTime 2019/03/24
 */
public class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.err.println(this + "installing engine");
        assembler.car().addEngine();
    }
}
