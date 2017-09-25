package com.lgx.jdk8.part02;

import java.util.*;
import java.util.function.*;

/**
 * java.util.function包API自学
 */
public class Test06LearnBySelf {
    public static void main(String[] args) {
        /**Consumer:接受一个参数不返回值，functional method is accept(Object)**/
        List<String> list = Arrays.asList("hello", "world");
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s.toUpperCase());
            }
        });
        list.forEach(item -> System.out.println(item.toUpperCase()));

        /**DoubleConsumer:接受一个Double参数不返回值，functional method is accept(Object)**/
        DoubleConsumer dc = (x) -> System.out.println(x*x);
        dc.accept(3.1415);
        List<Double> list2 = Arrays.asList(1.11, 2.22);
        /*list2.forEach(new DoubleConsumer(){
            @Override
            public void accept(double value) {

            }
        });*/ //一直报错？？？
        list2.forEach(item -> System.out.println(item));

        /**BiConsumer:接受两个参数不返回值，functional method is accept(Object)**/
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s1, String s2) {
                System.out.println("key = " + s1 + ", value = " + s2);
            }
        });
        map.forEach((s1, s2) -> System.out.println("key = " + s1 + ", value = " + s2));

        System.out.println("================================================================");

        /**Function:接受一个参数返回一个值，functional method is apply(Object)**/
        Function<String, String> function = (s1) -> s1.toUpperCase();
        String result = function.apply("hello world");
        System.out.println("result = " + result);

        /**IntFunction:接受一个Int参数返回一个Int值，functional method is apply(Object)**/
        IntFunction<Integer> intFunction = (int1) -> int1 * int1;
        int intResult = intFunction.apply(100);
        System.out.println("intResult = " + intResult);

        /**DoubleToIntFunction:接受一个double参数返回一个int值，functional method is applyAsInt(Object)**/
        DoubleToIntFunction doubleToIntFunction = (d1) -> (int)d1+2;
        int doubleToIntResult = doubleToIntFunction.applyAsInt(3.1415926);
        System.out.println("doubleToIntResult = " + doubleToIntResult);

        /**BiFunction:接受两个参数返回一个值，functional method is apply(Object)**/
        BiFunction<String, String, String> biFunction = (s1, s2) -> s1 + s2;
        String biResult = biFunction.apply("hello", "world");
        System.out.println("biResult = " + biResult);

        /**BinaryOperator(二元运算符):接受两个参数返回一个值（参数和返回值类型是一致的），functional method is apply(Object)**/
        BinaryOperator<Double> binaryOperator = (d1, d2) -> d1 + d2;
        double binaryResult = binaryOperator.apply(20.0, 30.0);
        System.out.println("binaryResult = " + binaryResult);
        double minResult = BinaryOperator.minBy(Double::compareTo).apply(20.0 , 30.0);
        System.out.println("minResult = " + minResult);
        double maxResult = BinaryOperator.maxBy(Double::compareTo).apply(20.0 , 30.0);
        System.out.println("maxResult = " + maxResult);

        System.out.println("================================================================");

        /**Predicate:接受一个参数返回一个boolean值，functional method is test(Object)**/
        Predicate<String> predicate = (a) -> a == null;
        boolean predicateResult = predicate.test(new String());
        System.out.println("predicateResult = " + predicateResult);

        /**Predicate:接受一个参数返回一个boolean值，functional method is test(Object)**/
        DoublePredicate doublePredicate = (d) -> d >= 10;
        boolean doublePredicateResult = doublePredicate.test(9.99999);
        System.out.println("doublePredicateResult = " + doublePredicateResult);

        /**BiPredicate:接受两个参数返回一个boolean值，functional method is test(Object)**/
        BiPredicate<Integer, Integer> biPredicate = (b1, b2) -> b1 > b2;
        boolean biPredicateResult = biPredicate.test(3, 2);
        System.out.println("biPredicateResult = " + biPredicateResult);

        System.out.println("================================================================");

        /**Supplier:不接受参数返回一个值，functional method is get(Object)**/
        Supplier<String> supplier = () -> "test";
        String supplierResult = supplier.get();
        System.out.println("supplierResult = " + supplierResult);

        /**DoubleSupplier:不接受参数返回一个Double值，functional method is getAsDouble(Object)**/
        DoubleSupplier doubleSupplier = () -> 1;
        double doubleSupplierResult = doubleSupplier.getAsDouble();
        System.out.println("doubleSupplierResult = " + doubleSupplierResult);

        System.out.println("================================================================");



    }
}
