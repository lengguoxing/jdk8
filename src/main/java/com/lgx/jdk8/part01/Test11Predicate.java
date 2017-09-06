package com.lgx.jdk8.part01;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate的讲解，在上一个例子已经用到了
 */
public class Test11Predicate {
    public static void main(String[] args) {
        Predicate<String> predicate = p -> p.length() > 5;

        System.out.println("helloworld = [" + predicate.test("helloworld") + "]");


        Test11Predicate test = new Test11Predicate();
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        test.conditionFilter(list, item -> item % 2 == 0); //获取偶数
        test.conditionFilter(list, item -> item % 2 == 1); //获取奇数
        test.conditionFilter(list, item -> item > 5);      //获取大于5的数
        test.conditionFilter(list, item -> true);          //获取所有

        System.out.println("-----------------------");

        test.conditionAndFilter(list, item -> item % 2 == 0, item -> item > 5);       //获取大于5的偶数
        test.conditionAndFilter(list, item -> item % 2 == 0, item -> item % 3 == 0);  //获取能同时被2,和3整除的
        test.conditionOrFilter(list, item -> item % 2 == 0, item -> item % 3 == 0);   //获取能被2或者3整除的
        test.conditionAndNegateFilter(list, item -> item % 2 == 0, item -> item % 3 == 0);   //获取不能同时被2,和3整除的

        boolean b = test.isEqual("helloworld").test("helloworld");   //判断2个参数是否相等的
        System.out.println("b = [" + b + "]");
        boolean b2 = test.isEqual2(new Date()).test(new Date());   //判断2个参数是否相等的
        System.out.println("b2 = [" + b2 + "]");  //张龙老师的电脑运行是false，我的是true???

    }

    /**
     * 函数式编程有区别之前的面相对象编程：
     * 1：面相对象传递是参数，方法里面做具体的处理
     * 2：函数式编程传递是行为，由调用者来决定怎么处理
     * @param list
     * @param predicate
     */
    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer : list){
            if(predicate.test(integer)){
                System.out.print(integer + ", ");
            }
        }
        System.out.println("");
    }

    public void conditionAndFilter(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2){
        for (Integer integer : list){
            if(predicate.and(predicate2).test(integer)){
                System.out.print(integer + ", ");
            }
        }
        System.out.println("");
    }

    public void conditionOrFilter(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2){
        for (Integer integer : list){
            if(predicate.or(predicate2).test(integer)){
                System.out.print(integer + ", ");
            }
        }
        System.out.println("");
    }

    public void conditionAndNegateFilter(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2){
        for (Integer integer : list){
            if(predicate.and(predicate2).negate().test(integer)){
                System.out.print(integer + ", ");
            }
        }
        System.out.println("");
    }


    public Predicate<String> isEqual(Object object){
        return Predicate.isEqual(object);//判断2个参数是否相等的
    }

    public Predicate<Date> isEqual2(Object object){
        return Predicate.isEqual(object);//判断2个参数是否相等的
    }

}
