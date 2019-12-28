package com.wy.newblog.test.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wy
 * @Description 原子更新引用类型
 * @createTime 2019/04/28
 */
public class AtomicReferenceTest {

    public static AtomicReference<User> atomicUserRef = new AtomicReference<>();

    /**
     * 代码中首先构建一个user对象，然后把user对象设置进AtomicReference中，最后调用
     * compareAndSet方法进行原子更新操作，实现原理同AtomicInteger里的compareAndSet方法。
     */
    public static void main(String[] args) {
        User user = new User("conan", 15);
        atomicUserRef.set(user);
        User updateUser = new User("Shinichi", 17);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getOld());
    }

    public static class User {

        private String name;

        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
