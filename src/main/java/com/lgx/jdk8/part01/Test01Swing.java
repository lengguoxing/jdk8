package com.lgx.jdk8.part01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JDK8的初体验
 * Created by Administrator on 2017/9/1.
 */
public class Test01Swing {
    //输入psvm会自动提示
    public static void main(String[] args) {
        //输入soutp会自动提示
        System.out.println("args = [" + args + "]");
        System.out.println("just a test");

        JFrame jFrame = new JFrame("My JFrame");
        JButton jButton1 = new JButton("My JBotton1");
        JButton jButton2 = new JButton("My JBotton2");

        //JDK1.8之前写法
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button1 Pressed e = [" + e + "]");
            }
        });

        //JDK1.8的写法
        jButton2.addActionListener(event -> System.out.println("Button2 Pressed e = [" + event + "]"));

        //完整的写法,一般java编译器推断出类型，但是也有情况推断不出来的情况，就需要我们显式的写出来
        jButton2.addActionListener((ActionEvent event) -> System.out.println("Button2 Pressed e = [" + event + "]"));

        //方法体多行
        jButton2.addActionListener(event -> {
            System.out.println("start");
            System.out.println("Button2 Pressed e = [" + event + "]");
            System.out.println("end");
        });

        //jFrame.add(jButton1);
        jFrame.add(jButton2);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
