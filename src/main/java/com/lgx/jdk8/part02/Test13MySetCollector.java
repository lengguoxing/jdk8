package com.lgx.jdk8.part02;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 自定义Collector，定义一个Set收集器
 */
public class Test13MySetCollector{
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome");

        Set<String> set = list.stream().collect(new MySetCollector<>());

        System.out.println("set = " + set);
    }
}

class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {
    //创建一个新的容器
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("Supplier supplier invoked");
        return HashSet<T>::new;
    }

    //累加器：添加一个元素到容器
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("Supplier accumulator invoked");
        //return HashSet<T>::add;//这里不能给一个具体的Set，因为supplier方法可能返回的是TreeSet，不一定是HashSet

        return Set<T>::add;
    }

    //把并行流多个结果合并
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("Supplier combiner invoked");
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    //完成器：合并完返回最终结果
    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("Supplier finisher invoked");
        //return t -> t;
        return Function.identity();//与上面那个等价
    }

    //返回一个集合，标识这个集合的诸多特性
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("Supplier characteristics invoked");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }

}
