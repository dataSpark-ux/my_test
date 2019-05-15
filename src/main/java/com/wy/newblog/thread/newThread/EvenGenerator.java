package com.wy.newblog.thread.newThread;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/02
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int next() {

        ++currentEvenValue; // 危险点在这
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
