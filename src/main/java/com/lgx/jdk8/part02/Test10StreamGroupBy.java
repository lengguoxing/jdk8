package com.lgx.jdk8.part02;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Stream的分组，分区
 */
public class Test10StreamGroupBy {
    public static void main(String[] args) {
        User user1 = new User("zhangsan", 60, 20);
        User user2 = new User("lisi", 80, 23);
        User user3 = new User("zhangsan", 80, 24);
        User user4 = new User("wangwu", 50, 24);

        List<User> userList = Arrays.asList(user1, user2, user3, user4);

        //按照名字分组
        Map<String, List<User>> nameMap = userList.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println("nameMap = " + nameMap);

        //按照分数分组
        Map<Integer, List<User>> scoreMap = userList.stream().collect(Collectors.groupingBy(User::getScore));
        System.out.println("scoreMap = " + scoreMap);

        //按照名字分组，并求出个数
        Map<String, Long> nameMap2 = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.counting()));
        System.out.println("nameMap2 = " + nameMap2);

        //按照名字分组，分数平局值
        Map<String, Double> nameMap3 = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.averagingDouble(User::getScore)));
        System.out.println("nameMap3 = " + nameMap3);

        /**分区：分区是分组的一种特殊情况，分组可以分成多组，分区只分成2组**/
        //及格的人
        Map<Boolean, List<User>> map4 = userList.stream().collect(Collectors.partitioningBy(user -> user.getScore() >= 60));
        System.out.println("map4 = " + map4);

    }
}

class User{
    private String name;
    private int score;
    private int age;
    public User(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}