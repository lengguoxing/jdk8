package com.lgx.jdk8.part02;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 并行Stream和串行Stream的执行效率
 */
public class Test07StreamParallel {
    public static void main(String[] args) {
        int size = 5000000;
        List<String> uuisList = new ArrayList<>(size);

        System.out.println("开始生成 = " + new Date());
        //生成500万个uuid
        for (int i = 0; i< 5000000; i++){
            uuisList.add(UUID.randomUUID().toString());
        }
        System.out.println("生成结束 = " + new Date());

        /*System.out.println("开始串行排序");
        //long starttime = System.currentTimeMillis();//毫秒
        long startTime = System.nanoTime();//纳秒，更为精确

        uuisList.stream().sorted().count();//串行排序

        long endTime = System.nanoTime();//纳秒，更为精确

        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("结束串行排序 耗时为 " + distanceTime);*/

        System.out.println("开始并行排序");
        //long starttime = System.currentTimeMillis();//毫秒
        long startTime = System.nanoTime();//纳秒，更为精确

        uuisList.parallelStream().sorted().count();//并行排序

        long endTime = System.nanoTime();//纳秒，更为精确

        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("结束并行排序 耗时为 " + distanceTime);



    }


}
