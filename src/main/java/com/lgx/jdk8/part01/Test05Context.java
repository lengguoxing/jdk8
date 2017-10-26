package com.lgx.jdk8.part01;


/**
 * JDK8中lambda的理解
 */
public class Test05Context {
    public static void main(String[] args) {
        //() -> {};
        // 这里都定义一种的lambda类型，但是单独这么写是报错的， 编译器无法识别，
        // 必须通过上下文来识别，这里的上下文，就是写在它前面定义的对象

        Test05MyInterface interface1 = () -> {};
        System.out.println(interface1.getClass().getInterfaces()[0]);
        System.out.println(interface1.getClass().getMethods()[0]);

        System.out.println("----------------");

        Test05MyInterface2 interface2 = () -> {};
        System.out.println(interface2.getClass().getInterfaces()[0]);
        System.out.println(interface2.getClass().getMethods()[0]);

        System.out.println("----------------");

        //传统的线程写法是
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello thread");
            }
        }).start();

        //JDK8的写法
        new Thread(() -> {
            System.out.println("hello thread2");
        }).start();

    }
}

@FunctionalInterface
interface Test05MyInterface {
    void myMethod();

}

@FunctionalInterface
interface Test05MyInterface2 {
    void myMethod2();

}


