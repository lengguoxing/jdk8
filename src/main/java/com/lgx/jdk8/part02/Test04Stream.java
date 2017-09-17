package com.lgx.jdk8.part02;

import java.util.*;
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
        strList.forEach(System.out::println);*/

        //用collect(),参数方法引用来写
        /*List<String> strList2 = streams.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        strList2.forEach(System.out::println);*/

        //用collect()配合Collectors.toList()来写
        /*List<String> strList3 = streams.collect(Collectors.toList());
        strList3.forEach(System.out::println);*/

        //用collect()配合Collectors.toCollection来写,可以指定类型：如ArrayList
        /*List<String> strList4 = streams.collect(Collectors.toCollection(ArrayList::new));
        strList4.forEach(System.out::println);*/

        //转换成set
        /*Set<String> set = streams.collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("set type = [" + set.getClass() + "]");*/

        //转换成set,指定类型
        /*Set<String> set = streams.collect(Collectors.toCollection(TreeSet::new));
        set.forEach(System.out::println);
        System.out.println("set type = [" + set.getClass() + "]");*/

        //拼接
        String str = streams.collect(Collectors.joining());
        System.out.println("拼接str = [" + str + "]");

        //过滤，将每个参数由小写变成大写，map函数就是映射，针对每一个元素
        List<String> list2 = Arrays.asList("hello", "world", "hello world");
        list2.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        //过滤，将每个参数变成之前的平方
        List<Integer> list3 = Arrays.asList(2,3,4,5);
        list3.stream().map(item -> item * item).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("==========================");

        //flatMap操作,扁平化map，会将里面的集合合并到一个集合
        Stream<List<Integer>> stream5 = Stream.of(Arrays.asList(1,2,3), Arrays.asList(13,14), Arrays.asList(25,26,27));
        stream5.flatMap(theList -> theList.stream()).map(item -> item * item).forEach(System.out::println);

        /**
         * generate方法使用
         * findFirst之所以返回一个Optional,是因为避免NPE，空指针异常，因为可能一个都没找到
         */
        Stream<String> stream6 = Stream.generate(UUID.randomUUID()::toString);
        stream6.findFirst().ifPresent(System.out::println);

        //iterate方法使用
        Stream.iterate(1, item -> item + 2).limit(10).forEach(System.out::println);


    }

}
