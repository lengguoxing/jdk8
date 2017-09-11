package com.lgx.jdk8.part02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream的介绍
 */
public class Test04Stream {
    public static void main(String[] args) {
        //创建流的几种方式
        Stream stream1 = Stream.of("hello", "world", "hello world");

        String[] myArray = new String[]{"hello", "world", "hello world"};
        Stream stream2 = Stream.of(myArray);

        Stream stream3 = Arrays.stream(myArray);

        List<String> myList = Arrays.asList("hello", "world", "hello world");
        Stream stream4 = myList.stream();

        System.out.println("------------------------");

        //IntStream的使用
        IntStream.of(new int[]{3,4,5,6,7}).forEach(System.out::print);
        IntStream.range(3,8).forEach(System.out::print);
        IntStream.rangeClosed(3,7).forEach(System.out::print);

        System.out.println("------------------------");

        //每个数乘以2再求和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        //传统写法
        /*int sum = 0;
        for(Integer integer: list){
            sum += integer*2;
        }
        System.out.println("sum = [" + sum + "]");*/
        //jdk8写法
        System.out.println("sum = [" + list.stream().map(i -> i*2).reduce(0, Integer::sum) + "]");

        //流转换成集合，再遍历
        Stream<String> streams = Stream.of("hello", "world", "hello world");
        /*String[] strArray = streams.toArray(length -> new String[length]);
        Arrays.asList(strArray).forEach(System.out::println);*/

        //用方法引用来写
        /*String[] strArray2 = streams.toArray(String[]::new);
        Arrays.asList(strArray2).forEach(System.out::println);*/

        //用collect()另外方法来写
       /* List<String> strList = streams.collect(() -> new ArrayList(), (theList, item) -> theList.add(item),
                (theList1, theList2) -> theList1.addAll(theList2));
        Arrays.asList(strList).forEach(System.out::println);*/

        //用collect(),参数方法引用来写
        /*List<String> strList2 = streams.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        Arrays.asList(strList2).forEach(System.out::println);*/

        //用collect()配合Collectors.toList()来写
        /*List<String> strList3 = streams.collect(Collectors.toList());
        Arrays.asList(strList3).forEach(System.out::println);*/



    }


}
