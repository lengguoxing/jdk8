package com.lgx.jdk8.part01;

import java.util.function.Supplier;

/**
 * 函数式接口Supplier
 */
public class Test12Supplier {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hello world";

        //get方法不接受参数，返回一个结果
        System.out.println("supplier = [" + supplier.get() + "]");

        //替代不接受参数的工厂方法
        Supplier<Student> studentSupplier = () -> new Student();
        System.out.println(studentSupplier.get());

        //因为Student的构造方法不接受参数，返回一个结果，符合Supplier接口的要求，可以简写如下：
        Supplier<Student> studentSupplier2 = Student::new;
        System.out.println(studentSupplier2.get());
    }
}

class Student{
    private String name = "zhangsan";
    private int age = 25;

    //删掉这个会报错
    public Student() {

    }

    //加上这个也不报错
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}