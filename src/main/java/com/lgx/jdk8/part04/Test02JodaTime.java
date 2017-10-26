package com.lgx.jdk8.part04;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;


/**
 * JodaTime的使用示例
 */
public class Test02JodaTime {
    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);
        System.out.println("tomorrow = " + tomorrow);
        System.out.println("tomorrow = " + tomorrow.toString("yyyy-MM-dd HH:mm:ss"));

        DateTime withDayOfMonth = today.withDayOfMonth(1);
        System.out.println("withDayOfMonth = " + withDayOfMonth.toString("yyyy-MM-dd HH:mm:ss"));

        //当前时区
        LocalDate localDate = new LocalDate();
        System.out.println("当前时区的日期localDate = " + localDate);

        LocalDate localDate2 = localDate.plusMonths(4).dayOfMonth().withMaximumValue();
        System.out.println("4个月后最后一天 localDate2 = " + localDate2);

        LocalDate localDate3 = localDate.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMaximumValue();
        System.out.println("2年前第三个月最后一天 localDate3 = " + localDate3);
        DateTime DateTime3 = today.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMaximumValue();
        System.out.println("2年前第三个月最后一天  DateTime3 = " + DateTime3.toString("yyyy-MM-dd HH:mm:ss"));





    }


}
