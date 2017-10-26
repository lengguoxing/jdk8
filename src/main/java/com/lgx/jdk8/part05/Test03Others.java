package com.lgx.jdk8.part05;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

/**
 * Java8时间其他API的一些用法
 */
public class Test03Others {
    public static void main(String[] args) {
        //YearMonth只关注年+月
        YearMonth yearMonth = YearMonth.now();
        System.out.println("yearMonth = " + yearMonth);
        System.out.println("yearMonth = " + yearMonth.lengthOfMonth());
        System.out.println("yearMonth = " + yearMonth.lengthOfYear());
        System.out.println("yearMonth = " + yearMonth.isLeapYear());

        YearMonth yearMonth2 = YearMonth.of(2017, 2);
        System.out.println("yearMonth2 = " + yearMonth2.lengthOfMonth());
        System.out.println("yearMonth2 = " + yearMonth2.lengthOfYear());

        //MonthDay只关注月+日
        LocalDate localDate = LocalDate.of(2017, 10, 26);
        MonthDay monthDay = MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2015, 10, 26));
        System.out.println("monthDay == monthDay2 " + monthDay.equals(monthDay2));

    }
}
