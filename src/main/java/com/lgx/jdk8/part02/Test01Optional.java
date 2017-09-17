package com.lgx.jdk8.part02;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Optional的使用
 */
public class Test01Optional {
    public static void main(String[] args) {
        //传统方法不为空判断和调用
        String hello = "hello";
        if(hello != null){
            System.out.println("hello = [" + hello + "]");
        }

        //Optional默认写法
        Optional<String> optional = Optional.of("hello");
        if(optional.isPresent()){
            System.out.println("optional = [" + optional.get() + "]");
        }

        //Optional推荐写法
        Optional<String> optional2 = Optional.of("hello");
        optional2.ifPresent(item -> System.out.println("optional2 = [" + optional2.get() + "]"));

        //Optional构造空对象
        Optional<String> optional3 = Optional.empty();
        //Optional<String> optional3 = Optional.ofNullable(null);
        optional3.ifPresent(item -> System.out.println("optional3 = [" + optional3.get() + "]"));

        //orElse使用
        System.out.println("optional3 orElse= [" + optional3.orElse("world") + "]");

        //orElseGet使用
        System.out.println("optional3 orElseGet= [" + optional3.orElseGet(() -> "nihao")+ "]");

        //orElseThrow使用
        //System.out.println("optional3 orElseThrow= [" + optional3.orElseThrow(() -> new NoSuchElementException())+ "]");

        System.out.println("----------------------------------");
        Employee employee = new Employee();
        employee.setName("zhangsan");

        Employee employee2 = new Employee();
        employee2.setName("lisi");

        Company company = new Company();
        company.setName("company");
        company.employeeList = Arrays.asList(employee, employee2);

        //以前写一个方法获取列表
        /*if(company.employeeList != null){
            return company.employeeList;
        }else{
            return new ArrayList<Employee>();
        }*/

        //函数式的写法
        Optional<Company> optional5 = Optional.ofNullable(company);
        System.out.println("optional5 = [" + optional5.map(theCompany -> theCompany.employeeList).orElse(Collections.emptyList()) + "]");
    }
}

class Employee{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Company{
    private String name;
    public List<Employee> employeeList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
