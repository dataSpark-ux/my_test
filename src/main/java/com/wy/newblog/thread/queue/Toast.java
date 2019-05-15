package com.wy.newblog.thread.queue;

/**
 * @author wy
 * @Description toast
 * @createTime 2019/04/03
 */
public class Toast {

    public enum Status{DRY,BUTTERED,JAMMED}

    private Status status = Status.DRY;

    private final int id;

    public Toast(int id) {
        this.id = id;
    }

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "id=" + id +": status"+status+
                '}';
    }
}
