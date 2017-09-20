package com.lgx.jdk8.part02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream的真实执行过程+Stream的短路原则
 */
public class Test08StreamShortCircuit {
    public static void main(String[] args) {
        //获取长度为5，并去取第一个，输出长度
        List<String> list = Arrays.asList("hello", "world", "hello world");

        //写法1
        //list.stream().filter(item -> item.length() == 5).limit(1).forEach(item -> System.out.println(item + " " + item.length()));

        //写法2
        //list.stream().mapToInt(item -> item.length()).filter(length -> length == 5).findFirst().ifPresent(System.out::println);

        //输出3次item
        /*list.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 6).findFirst().ifPresent(System.out::println);*/

        //只输出一次item,这是流的真实执行过程，流是会短路计算的
        list.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);


    }
}
