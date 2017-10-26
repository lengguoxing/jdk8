package com.lgx.jdk8.part04;

import org.joda.primitives.list.IntList;
import org.joda.primitives.list.impl.ArrayIntList;

import java.util.ArrayList;
import java.util.List;

/**
 * Joda包的使用示例
 */
public class Test01Joda {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.forEach(System.out::println);

        IntList intList = new ArrayIntList();
        intList.add(1);
        intList.add(2);
        intList.forEach(System.out::println);


    }
}
