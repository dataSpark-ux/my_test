package com.wy.newblog.Stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author
 * @Date 2018/11/22 10:26
 * @Description TODO
 * @Version 1.0
 */
public class Stream {
    List<User> users = new ArrayList<>();


    @Test
    public void test1() {
        // 筛选出性别是男的，返回一个新的集合
        List<User> manUsers = users.stream()
                .filter(u -> u.getName() != null)
                .collect(Collectors.toList());
    }


    @Test
    public void test2() {
        List<String> names = users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    @Test
    public void test3() {
        List<String> list = Arrays.asList("a", "a", "b", "c");
        List<String> collect = list.stream().collect(Collectors.toMap(n -> n, n -> 1, Integer::sum))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(k -> k.getKey())
                .collect(Collectors.toList());
        Map<String, Integer> collect1 = list.stream().collect(Collectors.toMap(n -> n, n -> 1, Integer::sum))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue()));
        collect.forEach(c -> System.err.println("c===" + c));

        Map<String, Long> collect2 = list.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        collect2.entrySet().forEach(c-> System.err.println("k=="+c.getKey()+"v=="+c.getValue()));
    }
}
