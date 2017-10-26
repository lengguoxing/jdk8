package com.lgx.jdk8.part04;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * JodaTime和Java时间转换的使用示例
 */
public class Test03TimeConvert {
    public static void main(String[] args) {
        /**标准的UTC时间：2017-10-26T09:58:20.765Z
         不带时区的，服务器最好是用这种时间来存储，并且传递给客户端，客户端识别自己的时区，再对这个时间进行加减时区操作，从而显示一个正确的时间
         **/
        String utctime = "2017-10-26T09:58:20.765Z";
        System.out.println("convertUTC2Date = " + convertUTC2Date(utctime));//自动加上时区,得到本地的时间

        Date date = new Date();//得到一个本地的时间
        System.out.println("date = " + date);
        System.out.println("convertDate2UTC = " + convertDate2UTC(date));//自动减掉时区，得到一个标准的UTC时间

        System.out.println("convertDate2LocalByDateFormat = " + convertDate2LocalByDateFormat(date, "yyyy-MM-dd HH:mm:ss"));// 格式化本地的时间


    }

    //utc时间转为JodaTime时间
    public static DateTime convertUTC2Date(String utcDate){
        try{
            DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime;
        }catch (Exception e){
            return null;
        }
    }

    //java时间转为JodaTime时间
    public static String convertDate2UTC(Date javaDate){
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }

    //java时间转为Local时间
    public static String convertDate2LocalByDateFormat(Date javaDate, String dateFormat){
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }


}
