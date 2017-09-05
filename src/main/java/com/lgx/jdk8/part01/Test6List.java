package com.lgx.jdk8.part01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JDK8中集合的遍历及流的初体验
 */
public class Test6List {
    public static void main(String[] args) {
        //下面一个小例子，是将list里面每个元素的字母变成大写，然后输出
        List<String> list = Arrays.asList("hello", "world", "hello world");
        //传统方法就是遍历，然后lowCase,然后输出

        //jdk8的写法
        list.forEach(item -> {
            System.out.println("item = [" + item.toUpperCase() + "]");
        });

        //需求2：构造一个新的集合，存放字母变成大写的数据
        List<String> list2 = new ArrayList<String>();
        list.forEach(item -> {
            list2.add(item.toUpperCase());
        });
        list2.forEach(item -> {
            System.out.println("item2 = [" + item.toUpperCase() + "]");
        });

        //以上的写法还不完全是JDK8的写法，更好的写法如下（采用流）：
        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println("item3 = [" + item + "]"));
        //再变一下
        list.stream().map(String::toUpperCase).forEach(item-> System.out.println("item4 = [" + item + "]"));

    }
}
