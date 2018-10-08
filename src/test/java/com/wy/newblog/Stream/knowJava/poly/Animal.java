package com.wy.newblog.Stream.knowJava.poly;

import org.junit.Test;

/**
 * @author
 * @Date 2018/10/8 14:02
 * @Description TODO
 * @Version 1.0
 */
public class Animal {

//这就是向上转型，Animal animal = new Cat();
// 将子类对象Cat转化为父类对象Animal。这个时候animal这个引用调用的方法是子类方法
    @Test
    public void main() {
//        Animals d = new Dog();
//        d.eat();
//        ((Dog) d).run();
//        Animals c = new Cat();
//        Cat cs = (Cat) c;
//        cs.run();

        eat(new Dog());
    }


    public void eat(Animals a) {
        if (a instanceof Dog) {
            Dog d = (Dog) a;
            d.eat();
            d.run();
        }
        if (a instanceof Cat) {
            Cat c = (Cat) a;
            c.run();
            c.eat();
        }
    }

}

class Cat extends Animals {
    public void eat() {
        System.err.println("我吃鱼");
    }
    public void run(){
        System.out.println("我会游");
    }
}

class Dog extends Animals {
    public void  eat() {
        System.err.println("我吃骨头");
    }

    public void run(){
        System.out.println("我会跑");
    }
}

class Animals{

    public void eat() {
        System.err.println("animal thisng");
    }

}