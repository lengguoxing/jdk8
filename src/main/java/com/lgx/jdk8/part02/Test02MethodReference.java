package com.lgx.jdk8.part02;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的学习
 */
public class Test02MethodReference {
    public String getString(Supplier<String> supplier){
        return supplier.get() + "test";
    }

    public String getString2(String str, Function<String, String> function){
        return function.apply(str);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        //之前学习Lambda的写法
        list.forEach(item -> System.out.println("item = [" + item + "]"));

        //方法引用的写法
        list.forEach(System.out::println);

        //方法引用共分为4类：
        //a、类名::静态方法名
        //当你使用的Lambda表达式恰好有一个静态方法可以完成，则可以使用这种方式
        Student student1 = new Student("zhangsan", 60);
        Student student2 = new Student("lisi", 80);
        Student student3 = new Student("wangwu", 50);
        Student student4 = new Student("zhaoliu", 78);

        List<Student> studentList = Arrays.asList(student1, student2, student3, student4);
        //studentList.sort((studentPara1, studentPara2) -> Student.compareStudentsByScore(studentPara1, studentPara2));//传统写法
        /*studentList.sort(Student::compareStudentsByScore);
        studentList.forEach(student -> System.out.println("student = [" + student.getScore() + "]"));*/

        //b、引用名(对象名)::实例方法名
        /*studentList.sort(new StudentComparator()::compareStudentsByScore);
        studentList.forEach(student -> System.out.println("student = [" + student.getScore() + "]"));*/

        //c、类名::实例方法名
        //与上面那个类似，Lambda表达式传递进来的第一个参数作为当前对象，当作调用者，
        // 第二及以后的参数才传递给被调用的方法
        /*studentList.sort(Student::compareByScore);
        studentList.forEach(student -> System.out.println("student = [" + student.getScore() + "]"));
        studentList.sort(Student::compareByName);
        studentList.forEach(student -> System.out.println("student = [" + student.getName() + "]"));*/

        //拓展下
        List<String> cites = Arrays.asList("jiangxi", "hubei", "shenzhen", "beijing");
        //Collections.sort(cites, (city1, city2) -> city1.compareTo(city2));//传统写法
        //cites.forEach(city -> System.out.println("city = [" + city + "]"));//传统写法
        Collections.sort(cites, String::compareTo);//这里是第3种写法
        cites.forEach(System.out::println);//这里是第2种写法

        //d、类名::new
        //也叫构造方法引用
        Test02MethodReference test = new Test02MethodReference();
        String result = test.getString(String::new);
        System.out.println("result = [" + result + "]");
        String result2 = test.getString2("hello", String::new);
        System.out.println("result2 = [" + result2 + "]");

    }
}

class Student{
    private String name;
    private int score;
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    //按照学生的成绩对学生进行排序
    public static int compareStudentsByScore(Student student, Student student2){
        return student.getScore() - student2.getScore();
    }
    //按照学生的名字对学生进行排序
    public static int compareStudentsByName(Student student, Student student2){
        return student.getName().compareTo(student2.getName());
    }

    //按照学生的成绩对学生进行排序
    public int compareByScore(Student student){
        return this.getScore() - student.getScore();
    }
    //按照学生的名字对学生进行排序
    public int compareByName(Student student){
        return this.getName().compareTo(student.getName());
    }
}

class StudentComparator{
    //按照学生的成绩对学生进行排序
    public int compareStudentsByScore(Student student, Student student2){
        return student.getScore() - student2.getScore();
    }
    //按照学生的名字对学生进行排序
    public int compareStudentsByName(Student student, Student student2){
        return student.getName().compareTo(student2.getName());
    }
}