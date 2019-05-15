package com.wy.newblog.thread.test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/14
 */
public class TellerManager implements Runnable {

    private ExecutorService exec;

    private CustomerLine customers;

    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();

    private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();

    private int adjustmentPeriod;

    private static Random random = new Random(47);

    public TellerManager(ExecutorService exec, CustomerLine customers,int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // start with a single teller
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellernumber() {
        // this is actually a control system
        // by adjusting the numbers. you can reveal s
        // rtability issues in the
        // control mechanism .if line is too long add another teller
        //这实际上是一个控制系统//通过调整数字。你可以在//控制机制中揭示稳定性问题。如果行太长则添加另一个出纳员
        if (customers.size() / workingTellers.size() > 2) {
            //如果出纳员休息或做另一份工作带回来
            if (tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            // 否则创建（雇用）新出纳员
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }
        // if line is short enough remove a teller
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }
        // 如果没有线我们只需要一个出纳员
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellernumber();
                System.out.println(customers+"{");
                for (Teller teller : workingTellers) {
                    System.out.println(teller.shortString() + "");
                }
                System.out.println("}");

            }
        } catch (InterruptedException e) {
            System.out.println(this+ "InterruptedException");
        }
        System.out.println(this+ "terminating");
    }

    @Override
    public String toString() {
        return "TellerManager{" +
                "customers=" + customers +
                ", workingTellers=" + workingTellers +
                ", tellersDoingOtherThings=" + tellersDoingOtherThings +
                ", adjustmentPeriod=" + adjustmentPeriod +
                '}';
    }
}
