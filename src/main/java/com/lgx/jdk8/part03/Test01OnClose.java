package com.lgx.jdk8.part03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的onClose方法
 */
public class Test01OnClose<T> {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.stream().onClose(() ->{
            System.out.println("aaa");
        }).onClose(() -> {
            System.out.println("bbb");
        }).forEach(System.out::println);
        //执行之所以不打印aaa和bbb，是因为没有调用close方法，是因为没有遇到一个异常

        List<String> list2 = Arrays.asList("hello2", "world2", "hello world2");
        try(Stream<String> stream2 = list2.stream()) {
            stream2.onClose(() -> {
                System.out.println("aaa2");
            }).onClose(() -> {
                System.out.println("bbb2");
            }).forEach(System.out::println);

            //stream2.forEach(System.out::println);//会报错，stream has already been operated upon or closed
        }



    }


}


