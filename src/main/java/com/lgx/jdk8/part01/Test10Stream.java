package com.lgx.jdk8.part01;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * 通过流和函数式编程的方法来完成查询过滤
 */
public class Test10Stream {
    public static void main(String[] args) {
        Person person1 = new Person("zhangsan", 20);
        Person person2 = new Person("lisi", 25);
        Person person3 = new Person("wangwu", 30);

        List<Person> list = Arrays.asList(person1, person2, person3);

        Test10Stream Test = new Test10Stream();
        List<Person> resultList = Test.getPersonsByUsername("zhangsan", list);
        resultList.forEach(person -> System.out.println("persons 1 = [" + person + "]"));

        System.out.println("--------------------");

        List<Person> resultList2 = Test.getPersonsByAge(25, list);
        resultList2.forEach(person -> System.out.println("persons 2 = [" + person + "]"));

        System.out.println("--------------------");

        List<Person> resultList3 = Test.getPersonsByAge2(25, list, (ageOfPerson, personList) ->
                personList.stream().filter(person -> person.getAge() >= ageOfPerson).collect(Collectors.toList()));
        resultList3.forEach(person -> System.out.println("persons 3 = [" + person + "]"));
    }

    //通过name查询列表中符合条件的对象
    public List<Person> getPersonsByUsername(String name, List<Person> persons){
        //先转换成流，过滤流里面每一个对象，把名字符合我们要求的过滤出来，得到一个流，最后把这些流组合成一个集合
        return persons.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
    }

    //通过age查询列表中符合条件的对象
    public List<Person> getPersonsByAge(int age, List<Person> persons){
        //先定义BiFunction
        //三个参数分别是年龄，输入的用户集合，输出的用户集合结果
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageOfPerson, personList) -> {
            return  personList.stream().filter(person -> person.getAge() >= ageOfPerson).collect(Collectors.toList());
        };
        //简写如下：
        BiFunction<Integer, List<Person>, List<Person>> biFunction2 = (ageOfPerson, personList) ->
              personList.stream().filter(person -> person.getAge() >= ageOfPerson).collect(Collectors.toList());

        //再应用bifunction
        return biFunction2.apply(age, persons);
    }

    //改进版
    public List<Person> getPersonsByAge2(int age, List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction){
        //再应用bifunction
        return biFunction.apply(age, persons);
    }

}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}