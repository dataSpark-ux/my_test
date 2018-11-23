package com.wy.newblog.Stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author
 * @Date 2018/11/14 14:43
 * @Description TODO
 * @Version 1.0
 */
public class faxinTest {

    static List<Apple> apples = Arrays.asList(new Apple());

    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static class CovariantReader<T>{

        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        CovariantReader<Fruit> cr = new CovariantReader<>();
        Fruit Appl = cr.readCovariant(apples);
        System.err.println(Appl);
        Fruit fruit1 = cr.readCovariant(fruits);
        System.err.println(fruit1);
    }

    public static void main(String[] args) {
        f2();
    }
//
//    static class Reader<T>{
//        T readExect(List<T> list) {
//            return list.get(0);
//        }
//    }
//    static void f1() {
//        Reader<Fruit> fruitReader = new Reader<>();
//    }
}

class Fruit{

}

class Apple extends Fruit {

}

class Orange extends Fruit {

}