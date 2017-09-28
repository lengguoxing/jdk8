package com.lgx.jdk8.part02;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Comparator比较器的使用
 */
public class Test12Comparator {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hi", "hello", "welcome");

        //升序排序，流的写法
        //list.stream().sorted(Comparator.comparing(str -> str)).collect(Collectors.toList()).forEach(System.out::println);

        //升序排序，默认写法
        /*Collections.sort(list);
        System.out.println("list = " + list);*/

        //自定义排序器,默认写法
        /*Collections.sort(list, (item1, item2) -> item1.length() - item2.length()); //升序
        Collections.sort(list, (item1, item2) -> item2.length() - item1.length());   //降序
        System.out.println("list = " + list);*/

        //自定义排序器,Comparator写法
        /*Collections.sort(list, Comparator.comparingInt(str -> str.length()));                     //升序
        Collections.sort(list, Comparator.comparingInt(str -> str.length()).reversed());            //降序,会报错，因为编译器在这里无法推断str的类型为String，而是推断出事一个Objcet
        Collections.sort(list, Comparator.comparingInt((String str) -> str.length()).reversed());   //降序,显示的指定一个类型*/

        //自定义排序器,Comparator写法2
        /*Collections.sort(list, Comparator.comparingInt(String::length));           //升序
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());  //降序
        System.out.println("list = " + list);*/

        //直接调用list的排序方法，Collections.sort()本质还是调用list.sort方法
        /*list.sort(Comparator.comparingInt(String::length));             //升序
        list.sort(Comparator.comparingInt(String::length).reversed());    //降序*/

        /**两层排序:先按照长度排序，再按照字符串顺序**/
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));   //不区分大小写的排序
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase)));
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())).
                thenComparing(Comparator.reverseOrder()));//和上一个结果是一样的，因为已经排好序了，最后一个就不起作用了
        System.out.println("list = " + list);

    }
}
