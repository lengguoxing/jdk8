package com.lgx.jdk8.part05;

import java.time.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Java8时间LocalTime的使用
 */
public class Test02LocalTime {
    public static void main(String[] args) {
        //当前时间加上3个小时30分钟
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);
        LocalTime localTime2 = localTime.plusHours(3).plusMinutes(30);
        System.out.println("localTime2 = " + localTime2);


        Clock clock = Clock.systemDefaultZone();
        System.out.println("clock = " + clock);
        System.out.println("clock = " + clock.millis());

        Instant instant = Instant.now();
        System.out.println("Instant = " + instant);//标准的UTC时间


        Set<String> set = ZoneId.getAvailableZoneIds();//没排序的时区列表
        //set.stream().forEach(System.out::println);
        Set<String> treeSet = new TreeSet<String>(){
            {
                addAll(set);
            }
        };
        //treeSet.stream().forEach(System.out::println);//排序的时区列表


        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println("zonedDateTime = " + zonedDateTime);


        ZoneId zoneId2 = ZoneId.of("Europe/London");
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(localDateTime, zoneId2);
        System.out.println("zonedDateTime2 = " + zonedDateTime2);

        //两个Instant间的一段时间
        Duration duration = Duration.between(zonedDateTime, zonedDateTime2);
        System.out.println("duration = " + duration.toDays());
        System.out.println("duration = " + duration.toHours());
        System.out.println("duration = " + duration.getSeconds());
        //System.out.println("duration = " + duration.get(ChronoUnit.SECONDS));




    }
}
