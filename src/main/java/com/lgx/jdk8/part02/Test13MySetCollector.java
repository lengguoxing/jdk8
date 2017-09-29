package com.lgx.jdk8.part02;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义Collector，定义一个Set收集器
 */
public class Test13MySetCollector{
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello", "welcome");

        Set<String> set = list.stream().collect(new MySetCollector<>());
        System.out.println("set = " + set);

        System.out.println("========================");

        List<String> list2 = Arrays.asList("hello", "world", "hello", "welcome", "a", "b", "c", "d");
        Set<String> set2 = new HashSet<>();
        set2.addAll(list2);
        System.out.println("set2 = " + set2);

        //如果parallel和sequential写多个，以最后一个为准，因为在实现中就是以一个boolean来判断的
        for (int i = 0; i < 1; i++) {//执行100次验证并行，加这个参数CONCURRENT报错问题
            //Map<String, String> map = set2.stream().collect(new MyMapCollector<>());              //串行
            //Map<String, String> map = set2.parallelStream().collect(new MyMapCollector<>());      //并行
            Map<String, String> map = set2.stream().sequential().collect(new MyMapCollector<>()); //串行
            //Map<String, String> map = set2.stream().parallel().collect(new MyMapCollector<>());     //并行
            System.out.println("map = " + map);
        }

        System.out.println("可运行线程数=总cpu-被占用的cpu = " + Runtime.getRuntime().availableProcessors());
    }
}

//自定义收集器，输入时Set，输出是Set
class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {
    //创建一个新的容器
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("MySetCollector supplier invoked");
        return HashSet<T>::new;
    }

    //累加器：添加一个元素到容器
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("MySetCollector accumulator invoked");
        //return HashSet<T>::add;//这里不能给一个具体的Set，因为supplier方法可能返回的是TreeSet，不一定是HashSet

        return Set<T>::add;
    }

    //把并行流多个结果合并
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("MySetCollector combiner invoked");
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    //完成器：合并完返回最终结果
    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("MySetCollector finisher invoked");
        //return t -> t;
        return Function.identity();//与上面那个等价

        //throw new UnsupportedOperationException();
    }

    //返回一个集合，标识这个集合的诸多特性

    /**
     * Characteristics有3个值：
     * CONCURRENT：表示可以并行收集
     * UNORDERED：元素不保证顺序的
     * IDENTITY_FINISH：表示会执行一个强制类型转换,会调用finisher()方法
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("MySetCollector characteristics invoked");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }
}

//自定义收集器，输入时Set，输出是Map
class MyMapCollector<T> implements Collector<T, Set<T>, Map<T, T>> {
    //创建一个新的容器
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("MyMapCollector supplier invoked");
        //return HashSet<T>::new;

        return () -> {
            System.out.println("***************");//串行这里只执行一次，并行这里执行多次
            return new HashSet<T>();
        };
    }

    //累加器：添加一个元素到容器
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("MyMapCollector accumulator invoked");

        //return Set<T>::add;
        return (set, item) -> {
            System.out.println("MyMapCollector accumulator set = " + set + " " + Thread.currentThread().getName());
            //报错在于这里调用(打印)了set，和下面的代码产生了一边修改一边迭代，删掉这个打印就好了
            set.add(item);
        };
    }

    //把并行流多个结果合并
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("MyMapCollector combiner invoked");
        return (set1, set2) -> {
            set1.addAll(set2);
            System.out.println("MyMapCollector combiner set1 = " + set1 + " set2 = " + set2);
            return set1;
        };
    }

    //完成器：合并完返回最终结果
    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("MyMapCollector finisher invoked");

        return set -> {
            Map map = new HashMap();
            set.stream().forEach(item -> map.put(item, item));
            return map;
        };
    }

    //返回一个集合，标识这个集合的诸多特性
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("MyMapCollector characteristics invoked");
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));//运行会报错，因为这样会执行强制类型转换，而事实上无法将一个Set强制转换成Map
        /**
         * Characteristics.CONCURRENT:
         * 如果上面调用parallelStream，不管有没有这个属性都是并行
         * 不加这个属性，是多个线程操作多个结果容器，combiner也会调用多次
         * 加了这个属性，是多个线程操作一个结果容器，combiner也无需调用了
         */
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));//合并方法执行多次
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));//合并方法不会执行，多次运行该示例可能抛出异常ConcurrentModificationException
        /**
         * ConcurrentModificationException：并发修改异常，一个线程修改在一个集合，另一个集合在迭代这个集合，就会报出这个异常
         * 不加CONCURRENT，多个容器，也就互不干扰了；加了就一个容器，就会出现这个问题
         */
    }
}