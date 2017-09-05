package com.lgx.jdk8.part01;

import java.util.function.Function;

/**
 * JDK8中lambda的使用和行为的传递
 */
public class Test7FunctionInterface {
    public static void main(String[] args) {

        Function<String, String> function = String::toUpperCase;
        System.out.println("function = [" + function.getClass().getInterfaces().length + "]");
        System.out.println("function = [" + function.getClass().getInterfaces()[0] + "]");

        /**
         * toUpperCase()是String的一个普通方法，我们通过String.toUpperCase()是无法访问的，
         * 只能通过String的一个实例来访问，比如“aa123".toUpperCase()
         * 但是我们可以通过方法引用直接调用；形式为 类::方法
         */

        Test7FunctionInterface test7 = new Test7FunctionInterface();
        //使用statement
        int result = test7.compute(10, value -> {return value * 2;});//这里的apply()执行的就是value * 2
        System.out.println("result = [" + result + "]");

        //使用expression
        int result2 = test7.compute(10, value -> value + 2);         //这里的apply()执行的就是value + 2
        System.out.println("result2 = [" + result2 + "]");


        String result3 = test7.convert(100, value -> String.valueOf(value+"helloworld"));
        System.out.println("result3 = [" + result3 + "]");

        /**
         * 高阶函数：如果一个函数接收一个函数作为参数，或者返回一个函数作为返回值，那么该函数就叫做高阶函数
         * 在这里compute(),convert()都可以称之为高阶函数
         */

        //limbda其实可以先定义好，再去传递
        Function<Integer, Integer> function2 = value -> {return value * 2;};
        int result4 = test7.compute(20, function2);
        System.out.println("result4 = [" + result4 + "]");

    }

    //传递一个参数和一个行为
    public int compute(int a, Function<Integer, Integer> function){
        int result = function.apply(a);

        return result;
    }

    //传递一个参数和一个行为
    public String convert(int a, Function<Integer, String> function){
        String result = function.apply(a);

        return result;
    }


}
