package com.lgx.jdk8.part05;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Java8时间LocalDate的使用
 */
public class Test01LocalDate {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);
        System.out.println("localDate = " + localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth());
        System.out.println("localDate = " + localDate.getYear() + "-" + localDate.getMonth() + "-" + localDate.getDayOfMonth());

        LocalDate localDate2 = LocalDate.of(2018, 9, 16);
        //LocalDate localDate2 = LocalDate.of(2018, 11, 16);
        System.out.println("localDate2 = " + localDate2);

        //两个时间的间隔
        Period period = Period.between(localDate, localDate2);
        System.out.println("period = " + period.getYears());
        System.out.println("period = " + period.getMonths());
        System.out.println("period = " + period.getDays());

        //当前时间加上1年1个月1天
        LocalDate localDate5 = localDate.plusYears(1).plusMonths(1).plusDays(1);
        System.out.println("localDate5 = " + localDate5);

        //当前时间加上2年
        LocalDate localDate5_2 = localDate.plus(2, ChronoUnit.YEARS);
        System.out.println("localDate5_2 = " + localDate5_2);

        //当前时间减掉1年1个月1天
        LocalDate localDate6 = localDate.minusYears(1).minusMonths(1).minusDays(1);
        System.out.println("localDate6 = " + localDate6);

        //当前时间减掉2月
        LocalDate localDate6_2 = localDate.minus(2, ChronoUnit.MONTHS);
        System.out.println("localDate6_2 = " + localDate6_2);

        LocalDate localDate7 = LocalDate.of(2017, 10, 26);
        System.out.println(localDate7.isAfter(localDate));
        System.out.println(localDate7.isBefore(localDate));
        System.out.println(localDate7.isEqual(localDate));
        System.out.println(localDate7.isLeapYear());


    }
}
