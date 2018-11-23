package com.wy.newblog.Stream;

import com.wy.newblog.entity.UserEntity;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author
 * @Date 2018/9/13 14:41
 * @Description TODO
 * @Version 1.0
 */
public class test {

    private Persion p = new Persion("aa",null,18,0);
    private Persion p3 = new Persion("aa",null,19,0);
    private Persion p1 = new Persion("bb",null,20,0);
    private Persion p2 = new Persion(null,"cc",21,0);
    List<Persion> ps = new ArrayList<>();

    List<String> list = new ArrayList<String>();

    /**
      * 过滤出符合条件的
     */
    @Test
    public void test1() {
        ps.add(p);
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        List<Persion> collect = ps.stream().filter(Persion::isStudent).distinct().collect(Collectors.toList());
        collect.forEach(a-> System.err.println(a.getStudent()));
    }
    /**
      * 只获取学生
     *
     */
    @Test
    public void test3(){
        ps.add(p);
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        List<String> collect = ps.stream().map(Persion::getStudent).collect(Collectors.toList());
        collect.forEach(c-> System.err.println(c));
    }
    @Test
    public void test4(){
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");
        Stream<String[]> stream = list.stream().map(l -> l.split("/"));
        stream.forEach(a-> System.err.println(a));
    }

    @Test
    public void test5(){
        ps.add(p);
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        //一个符合就返回true
        boolean b = ps.stream().anyMatch(Persion::isStudent);
        //全部符合才返回true
        boolean a = ps.stream().allMatch(Persion::isStudent);
        System.err.println(b+"=="+a);
        Optional<Persion> any = ps.stream().findAny();
        System.err.println(any.get().getStudent());
    }
    @Test
    public void test6(){
        ps.add(p);
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        // 改为int流
        IntStream intStream = ps.stream().mapToInt(Persion::getAge);
        //获取最大的年龄
        OptionalInt max = ps.stream().mapToInt(Persion::getAge).max();
        intStream.forEach(a-> System.err.println(a));

        System.err.println(max.getAsInt());
    }
    @Test
    public void test7(){
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
        distinct().mapToInt(num -> num * 2).
        peek(System.out::println).skip(2).limit(4).sum());
    }
    @Test
    public void test8() {
        String aa = "bbb";
        String cc = "bbb";
        System.err.println(aa.equals(cc));

        short s1 = 1;
//        s1 = s1 + 1;
        s1 += 1;
        System.err.println(s1);
    }
    @Test
    public void test9(){
        faxin faxin = new faxin();
        faxin[] fs = {new faxin(), new faxin()};
        int i = countGreaterThan(fs, faxin);
        System.err.println("i===" + i);
    }

    @Test
    public  void test10() {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true
    }

    public static <T> void copy(List<? super T> dest,List<? extends T> src){
        for(int i = 0; i < src.size(); i++){
            dest.set(i,src.get(i));
        }
    }

    public  <T extends Comparable<T>> int countGreaterThan(T[] anArray,T elem) {
        int count = 0;
        for (T e : anArray) {
            int i = e.compareTo(elem);
            if (e.compareTo(elem) > 0) {
                ++count;
            }
        }
        return count;
    }
}
