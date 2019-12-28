package com.wy.newblog.thread.ThreadUtils.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author wy
 * @Description 正常的锁在任何时候都知允许一个任务访问一项资源，
 * 而计数信号量允许n个任务同时访问这个资源。你还可以将信号量看作是在向外分发使用资源的"许可证"，尽管实际上没有任何许可证对象
 * 作为一个示例，请考虑对象池的概念，它管理着数量有限的对象，
 * 当要使用对象时可以签出它们，在用户使用完毕时，可以将它们签回，这种功能可以被封装到一个泛型类中
 * <p>
 * <p>
 * 在这个简化的形式中，构造器使用newInstance()来把对象加载到池中。
 * 如果你需要一个新对象，那么可以调用checkOut().并且在使用完之后，将其递交给checkIn().
 * <p>
 * boolean类型的数组checkedOut 可以跟踪被签出的对象，并且可以通过getItem()和release()方法来管理。
 * 而这些将由Semaphore类型的available来加以确保。因此，在checkOut()中。如果没有任何信号量许可证可用（这意味着池中没有更多对象了）
 * available将阻塞调用过程。在checkIn()中，如果被签入的对象有效，则会向信号量返回一个许可证。
 * <p>
 * 为了创建一个示例，我们可以用Fat,这是一种创建代价高昂的对象类型，因为它的构造器运行起来很耗时
 * @createTime 2019/04/14
 */
public class Pool<T> {

    private int size;

    private List<T> items = new ArrayList<>();

    private volatile boolean[] checkedOut;

    private Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        // 加载具有可以检出的对象的池
        try {
            for (int i = 0; i < size; i++) {
                items.add(classObject.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    public void checkIn(T x) {
        if (releaseItem(x)) {
            available.release();
        }
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1) {
            return false;
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        // wasn't checked out
        return false;
    }

    public synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        // 信号量阻止到达这里
        return null;
    }
}
