package com.wy.newblog.thread.mode;


/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/11
 */
public class Test {
    public static void main(String[] args)  {
        new Circle();
    }
}

class Draw {

    public Draw(String type) {
        System.out.println(type+" draw constructor");
    }
}

class Shape {
    private Draw draw = new Draw("shape");
    public static String a = "a";
    public Shape(){
        System.out.println("shape constructor");
    }
}

class Circle extends Shape {

    private Draw draw = new Draw("circle");
    public Circle() {
        System.out.println("circle constructor"+a);
    }

}
