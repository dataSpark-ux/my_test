package com.wy.newblog.Stream.knowJava.poly;

/**
 * @author
 * @Date 2018/10/8 14:27
 * @Description
 *
 * 多态，简而言之就是同一个行为具有多个不同表现形式或形态的能力。
 * 多态的分类：运行时多态和编译时多态。
 * 运行时多态的前提：继承（实现），重写，向上转型
 * 向上转型与向下转型。
 * 继承链中对象方法的调用的优先级：this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。

 */
public class Demo {

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        /**
         *
         * 当父类对象引用变量引用子类对象时，被引用对象的类型决定了调用谁的成员方法，
         * 引用变量类型决定可调用的方法。如果子类中没有覆盖该方法，那么会去父类中寻找。
         * 继承链中对象方法的调用的优先级：
         * this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
         * */
        System.err.println("1--" + a1.show(b));
        System.err.println("2--" + a1.show(c));
        System.err.println("3--" + a1.show(d));
        System.err.println("4--" + a2.show(b));
        System.err.println("5--" + a2.show(c));
        System.err.println("6--" + a2.show(d));
        System.err.println("7--" + b.show(b));
        System.err.println("8--" + b.show(c));
        System.err.println("9--" + b.show(d));
    }

}
class A {
    public String show(A obj) {
        return "A and A";
    }

    public String show(D obj) {
        return "D and A";
    }
}

class B extends A {
    public String show(B obj) {
        return "B and B";
    }

    public String show(A obj) {
        return "B an A";
    }
}

class C extends B {

}

class D extends C {

}
