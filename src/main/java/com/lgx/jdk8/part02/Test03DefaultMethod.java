package com.lgx.jdk8.part02;

/**
 * 默认方法使用
 */
public class Test03DefaultMethod implements MyDefaultMethodInterface, MyDefaultMethodInterface2{
//public class Test03DefaultMethod extends MyDefaultMethodInterfaceImpl implements MyDefaultMethodInterface2{//继承类的优先级高于实现类
    public static void main(String[] args) {
        Test03DefaultMethod test = new Test03DefaultMethod();

        //如果只实现了一个接口，会调用该接口的默认方法，
        //如果实现了2个接口，并且有2个相同名字的默认方法，就要重写该方法
        test.method2();
    }

    @Override
    public void method() {

    }

    @Override
    public void method2() {
        //如果在重写的时候，想实现某一个接口的方法，可以这么写
        MyDefaultMethodInterface2.super.method2();
    }
}

interface MyDefaultMethodInterface{
    void method();

    default void method2(){
        System.out.println("MyDefaultMethodInterface hello world");
    }
}

interface MyDefaultMethodInterface2{
    void method();

    default void method2(){
        System.out.println("MyDefaultMethodInterface2 hello world");
    }
}

class MyDefaultMethodInterfaceImpl implements MyDefaultMethodInterface{
    public void method(){
        System.out.println("MyDefaultMethodInterfaceImpl method hello world");
    }

    public void method2(){
        System.out.println("MyDefaultMethodInterfaceImpl method2 hello world");
    }
}
