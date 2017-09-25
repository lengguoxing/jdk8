package com.lgx.jdk8.part01;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * JDK8中运用lambda操作集合的排序
 */
public class Test08Comparator {
    public static void main(String[] args) {
        //下面做一个排序的例子，来讲解Collection中的sort
        List<String> names = Arrays.asList("zhangsan", "lisi", "wangwu");
        /*Collections.sort(names, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);//倒叙
            }
        });
        System.out.println("names = [" + names + "]");*/

        //用JDK8中Limbda的写法，
        /*Collections.sort(names, (String o1, String o2) -> {
            return o2.compareTo(o1);//倒叙
        });
        System.out.println("names2 = [" + names + "]");*/

        //其实因为JVM会根据names里面都是String类型，推断出o1,o2的类型，所以可以省列
        /*Collections.sort(names, (o1, o2) -> {
            return o2.compareTo(o1);//倒叙
        });
        System.out.println("names2 = [" + names + "]");*/

        /*Collections.sort(names, (o1, o2) -> o2.compareTo(o1));
        System.out.println("names2 = [" + names + "]");*/

        //更加符合Java8新特性的的写法是：
        Collections.sort(names, Comparator.reverseOrder());
        System.out.println("names3 = [" + names + "]");


        //在这里我们看到body只有一条语句的时候可以写成o2.compareTo(o1)，不需要括号，不需要return，我们称之为expression
        //还有一种statement 则必须写全：{return o2.compareTo(o1);}

    }
}
