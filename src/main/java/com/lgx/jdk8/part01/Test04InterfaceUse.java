package com.lgx.jdk8.part01;

/**
 * JDK8中FunctionalInterface注解的使用
 */
public class Test04InterfaceUse {
    public void myTest(Test04MyInterface myInterface){
        System.out.println("==begin");
        myInterface.test();
        System.out.println("==end");
    }

    public static void main(String[] args) {
        Test04InterfaceUse test = new Test04InterfaceUse();

        test.myTest(new Test04MyInterface() {
            @Override
            public void test() {
                System.out.println("test");
            }
        });
        System.out.println("-------------");

        //lambda缩写如下，因为这里只有一个抽象方法，所以一定会去调用抽象test()
        test.myTest(() -> {
            System.out.println("test2");
        });
        System.out.println("-------------");

        //上面的匿名内部类也等价于
        Test04MyInterface myInterface = () -> {
            System.out.println("hello");
        };
        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces().length);
        System.out.println(myInterface.getClass().getInterfaces()[0]);
    }
}

@FunctionalInterface
interface Test04MyInterface {
    void test();

    //会报错，因为函数式接口只能有一个抽象方法
    //void test2();

    //不会报错，因为他是继承java.lang.Object对象的方法，并不会算到抽象方法里面去
    String toString();
}


