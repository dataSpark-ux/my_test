package com.wy.newblog.thread.ThreadUtils.scheduled;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author wy
 * @Description 第10章介绍过可以应用于假想温室的控制系统的示例，他可以控制各种设施的开关
 * 或者是对他们进行调节。这可以被看作是一种并发问题，每个期望的温室事件都是一个在预定时间运行的任务。
 * ScheduledThreadPoolExecutor提供了解决问题的服务，通过使用schedule()(运行一次任务)
 * 或者scheduledAtFixedPoolExecutor()(每隔规则的时间重复执行任务)，你可以将Runnable对象设置为在将来的某个时刻执行。
 * @createTime 2019/04/11
 */
public class GreenhouseScheduler {

    private volatile boolean light = false;

    private volatile boolean water = false;

    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return thermostat;
    }
    public synchronized void setThermostat(String value) {
        thermostat = value;
    }
    ScheduledThreadPoolExecutor scheduler =
            new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable {
        @Override
        public void run() {
            // 把硬件控制代码放在这里
            // 打开灯
            System.out.println("转动灯");
            light = true;
        }
    }

    class lightOff implements Runnable {
        @Override
        public void run() {
            // 把硬件控制代码放在这里
            // 关
            System.out.println("关灯");
            light = false;
        }
    }

    class WaterOn implements Runnable {
        @Override
        public void run() {
            // 把硬件控制代码放在这里
            System.out.println("打开温室水");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        @Override
        public void run() {
            System.out.println("关上温室水");
            water = false;
        }
    }

    class ThermostatNight implements Runnable {

        @Override
        public void run() {
            // 把硬件控制代码写在这
            System.out.println("恒温器到夜间设置");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            System.out.println("白天");
            setThermostat("day");
        }
    }

    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println("Bing!");
        }
    }

    List<DataPoint> data = Collections.synchronizedList(new ArrayList<>());
    class Terminate implements Runnable {
        @Override
        public void run() {
            System.out.println("Terminating");
            scheduler.shutdownNow();
            // 必须开始一项单独的任务来完成这项工作
            // 由于调度程序已关闭
            new Thread() {
                public void run() {
                    for (DataPoint datum : data) {
                        System.out.println(datum);
                    }
                }
            }.start();
        }
    }

    static class DataPoint {
        final Calendar time;

        final float temperature;

        final float humidity;

        public DataPoint(Calendar time, float temperature, float humidity) {
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "DataPoint{" +
                    "time=" + time +
                    ", temperature=" + temperature +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    private Calendar lastTime = Calendar.getInstance();

    {
        lastTime.set(Calendar.MINUTE,30);
        lastTime.set(Calendar.SECOND,00);
    }

    private float lastTemp = 65.0f;

    private int tempDirection = +1;

    private float lastHumidity = 50.0f;

    private int humidityDirection = +1;

    private Random random = new Random(47);

    class CollectData implements Runnable {
        @Override
        public void run() {
            System.out.println("collections data");
            synchronized (GreenhouseScheduler.this) {

            }
        }
    }

}
