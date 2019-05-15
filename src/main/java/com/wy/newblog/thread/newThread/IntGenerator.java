package com.wy.newblog.thread.newThread;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/02
 */
public abstract class IntGenerator {

    private volatile boolean canceled = false;


    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

}
