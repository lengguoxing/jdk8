package com.lgx.jdk8.part01;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Function和BiFunction的使用
 */
public class Test09Function {
    public static void main(String[] args) {
        //这个例子讲解Function中compose和andThen的区别
        Test09Function test = new Test09Function();

        int result = test.functionCompose(10, value -> value * 2, value -> value * value);
        System.out.println("result = [" + result + "]");

        int result2 = test.functionAndThen(10, value -> value * 2, value -> value * value);
        System.out.println("result2 = [" + result2 + "]");

        System.out.println("---------------------------");
        /**
         *  我们调用Function的apply(a)可以传入一个参数得到一个结果，但是这个类无法做到输入2个参数，得到一个结果，
         *  BiFunction可以做到这一点。Bi==Bidirectional双向的意思
         */
        int result3 = test.biFunctionCaculate(30, 20, (value1, value2) -> value1 + value2);
        System.out.println("result3 = [" + result3 + "]");

        int result4 = test.biFunctionCaculate(30, 20, (value1, value2) -> value1 - value2);
        System.out.println("result4 = [" + result4 + "]");

        int result5 = test.biFunctionAndThen(30, 20, (value1, value2) -> value1 - value2, value -> value * value);
        System.out.println("result5 = [" + result5 + "]");

    }

    public int functionCompose(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        //(V v) -> apply(before.apply(v));
        // 先应用function2的apply，并将结果作为参数再应用function1的apply
        return function1.compose(function2).apply(a);
    }

    public int functionAndThen(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        //(T t) -> after.apply(apply(t))
        // 先应用function1的apply，并将结果作为参数再应用function2的apply
        return function1.andThen(function2).apply(a);
    }

    public int biFunctionCaculate(int a, int b, BiFunction<Integer, Integer, Integer> biFunction){
        return biFunction.apply(a, b);
    }

    //BiFunction没有compose方法
    /*@Deprecated
    public int biFunctionCompose(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function){
        //return biFunction.compose(function).apply(a, b);
    }*/

    public int biFunctionAndThen(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function){
        return biFunction.andThen(function).apply(a, b);
    }



}
