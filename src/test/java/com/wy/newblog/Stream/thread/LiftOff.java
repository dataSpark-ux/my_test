package com.wy.newblog.Stream.thread;

/**
 * @suther: wy
 * @Date: 2018/10/7 15
 * @Description: 当从Runnable到处一个类时，他必须具有run()方法，但是这个方法并无特殊之处
 * 不会产生任何内在的线程能力，要实现线程行为，必须显示的将一个任务附到线程上
 */
public class LiftOff implements Runnable {
    //default
    protected int countDown = 10;

    private static int taskCount = 0;

    private final int id = taskCount++;

    public LiftOff() {

    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiffOff") + "),";
    }
    /**
     * 此方法通常总会有某种形式的循环，使得任务一直运行下去直到不再需要，
     * 所以要设定跳出循环的条件（有一种选择是直接从run()返回），通常，run()被挟持无限循环的形式
     * 这就意味着，除非有某个条件使得run()z终止，否则它将永远运行下去
     *
     * */
    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            /**
             *在run()中对静态方法yield()的调用是对线程调度器（java线程机制的一部分，可以将cpu从一个线程转移给另一个一个线程）的一种建议，
             * 它在申明：“我已经执行完生命周期中最重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机。”这完全是选择性的，
             * 但是这里使用他是因为它会在这些示例中产生更加有趣的输出：你更有可能看见任务换进换出的证据
             * */
            Thread.yield();
        }
        System.out.println();
    }
}
