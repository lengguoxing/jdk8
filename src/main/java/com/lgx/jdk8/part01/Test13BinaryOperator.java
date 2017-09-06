package com.lgx.jdk8.part01;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;


/**
 * Created by Administrator on 2017/9/6.
 */
public class Test13BinaryOperator {
    public static void main(String[] args) {
        Test13BinaryOperator test = new Test13BinaryOperator();
        Double result = test.biFunctionCompute(10.0, 15.0, (value1, value2) -> value1 + value2);
        System.out.println("result = [" + result + "]");

        Double result2 = test.binaryOperatorCompute(10.0, 15.0, (value1, value2) -> value1 + value2);
        System.out.println("result2 = [" + result2 + "]");

        Double minResult = test.binaryOperatorMinBy(100.0, 150.0, Double::compareTo);
        System.out.println("数字较小minResult = [" + minResult + "]");

        Double maxResult = test.binaryOperatorMaxBy(100.0, 150.0, Double::compareTo);
        System.out.println("数字较大maxResult = [" + maxResult + "]");

        String minResult2 = test.binaryOperatorMinBy2("hello", "hello world", (a, b) -> a.length() - b.length());
        System.out.println("长度较短minResult2 = [" + minResult2 + "]");
        String minResult3 = test.binaryOperatorMinBy2("hello", "hello world", String::compareTo);
        System.out.println("长度较短minResult3 = [" + minResult3 + "]");
        String minResult4 = test.binaryOperatorMinBy2("hello", "world123", (a, b) -> a.charAt(0) - b.charAt(0));
        System.out.println("首字母在前面的minResult4 = [" + minResult4 + "]");

        String maxResult2 = test.binaryOperatorMaxBy2("hello", "hello world", String::compareTo);
        System.out.println("长度较长maxResult2 = [" + maxResult2 + "]");
    }

    //BiFunction写法
    public Double biFunctionCompute(Double para1, Double para2, BiFunction<Double, Double, Double> biFunction){
        return biFunction.apply(para1, para2);
    }

    //BinaryOperator写法
    public Double binaryOperatorCompute(Double para1, Double para2, BinaryOperator<Double> binaryOperator){
        return binaryOperator.apply(para1, para2);
    }

    //获取2个double中较小的那个
    public Double binaryOperatorMinBy(Double para1, Double para2, Comparator<Double> comparator){
        return BinaryOperator.minBy(comparator).apply(para1, para2);
    }

    //获取2个double中较大的那个
    public Double binaryOperatorMaxBy(Double para1, Double para2, Comparator<Double> comparator){
        return BinaryOperator.maxBy(comparator).apply(para1, para2);
    }

    public String binaryOperatorMinBy2(String para1, String para2, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(para1, para2);
    }

    public String binaryOperatorMaxBy2(String para1, String para2, Comparator<String> comparator){
        return BinaryOperator.maxBy(comparator).apply(para1, para2);
    }
}
