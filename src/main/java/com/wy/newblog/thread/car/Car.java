package com.wy.newblog.thread.car;

/**
 * @author wy
 * @Description 汽车
 * @createTime 2019/03/24
 */
public class Car {

    private final int id;

    private boolean engine = false, driveTrain = false, wheels = false;


    public Car(int id) {
        this.id = id;
    }

    public Car() {
        id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDiveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", engine=" + engine +
                ", driveTrain=" + driveTrain +
                ", wheels=" + wheels +
                '}';
    }
}
