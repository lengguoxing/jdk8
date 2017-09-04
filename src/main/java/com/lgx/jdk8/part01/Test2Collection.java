package com.lgx.jdk8.part01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test2Collection {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        //传统方式
        for (int i = 0; i < list.size(); i++) {
            System.out.println("i = [" + list.get(i) + "]");
        }
        System.out.println("------------");

        //JDK1.5开始出现
        for (Integer i : list) {
            System.out.println("i = [" + i + "]");
        }
        System.out.println("------------");

        //JDK1.8开始出现
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("i = [" + integer + "]");
            }
        });

        //这里并不是写一个new Consumer的一个实例，而是传入这个实例的accept方法的一个参数
        //list.forEach(consumer -> {  //按照上一个例子，应该是这么些，但是这里不是
        //list.forEach((Integer i) -> {//应该这样
        list.forEach( i -> { //也可以简写成这样。因为java编译器知道list里面的每个对象的类型
            System.out.println("i = [" + i + "]");
        });
        System.out.println("------------");

        //还可以这样，通过函数的方法引用
        list.forEach(System.out::println);
    }
}
