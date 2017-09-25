package com.lgx.jdk8.part01;

/**
 * JDK8中FunctionalInterface的作用及意义
 */
@FunctionalInterface
public interface Test03MyInterface {
    void test();

    //会报错，因为函数式接口只能有一个抽象方法
    //void test2();

    //不会报错，因为他是继承java.lang.Object对象的方法，并不会算到抽象方法里面去
    String toString();
}
