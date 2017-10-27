package com.lgx.jdk8.part02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *Stream的使用陷阱
 */
public class Test05StreamTrap {
    public static void main(String[] args) {
        Stream<Integer> stream8 = Stream.iterate(1, item -> item + 2).limit(10);
        /*System.out.println("stream8 = " + stream8);
        System.out.println("stream8 = " + stream8.filter(item -> item > 2));
        System.out.println("stream8 = " + stream8.distinct());//distinct去重的作用*/
        //执行2个操作，会报错，流一旦被关闭了就不能再去操作了，或者被操作了一次之后，在另外一个地方又重新去操作，

        //规避办法如下，当然最好写成链的形式
        System.out.println("stream8 = " + stream8);
        Stream<Integer> stream8_2 = stream8.filter(item -> item > 2);
        System.out.println("stream8_2 = " + stream8_2);
        Stream<Integer> stream8_3 = stream8_2.distinct();
        System.out.println("stream8_3 = " + stream8_3);

        List<String> list4 = Arrays.asList("hello", "world", "hello world");
        //正常打印
        //list4.stream().map(item -> item.substring(0, 1) + item.substring(2)).forEach(System.out::println);
        //啥都不打印，因为没有及早求值或者没有终止操作
        /*list4.stream().map(item ->{
            String result = item.substring(0, 1) + item.substring(2);
            System.out.println("test-------");
            return result;
        });*/
        //依次打印一个test，再输出一个元素
        /*list4.stream().map(item ->{
            String result = item.substring(0, 1) + item.substring(2);
            System.out.println("test-------");
            return result;
        }).forEach(System.out::println);*/

        //关于执行顺序
        //IntStream.iterate(0, i -> (i+1)%2).limit(10).distinct().forEach(System.out::println);//limit已经定义了个数，再去重，就不会无限循环
        //IntStream.iterate(0, i -> (i+1)%2).distinct().limit(10).forEach(System.out::println);//无限循环，因为distinct会一直等待前面的数字

    }
}
