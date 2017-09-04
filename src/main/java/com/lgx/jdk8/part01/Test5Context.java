package com.lgx.jdk8.part01;



public class Test5Context {
    public static void main(String[] args) {
        //() -> {};
        // 这里都定义一种的lambda类型，但是单独这么写是报错的， 编译器无法识别，
        // 必须通过上下文来识别，这里的上下文，就是我们它前面定义的对象

        Test5MyInterface interface1 = () -> {};
        System.out.println(interface1.getClass().getInterfaces()[0]);
        System.out.println(interface1.getClass().getMethods()[0]);

        System.out.println("----------------");

        Test5MyInterface2 interface2 = () -> {};
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
interface Test5MyInterface {
    void myMethod();

}

@FunctionalInterface
interface Test5MyInterface2 {
    void myMethod2();

}


