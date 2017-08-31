package com.tang.callback;

import java.util.Date;

/**
 * Created by TangXW on 2017/8/31.
 */
public class SpinLock {
    public static String sharedVariable = null;

    public static void main(String[] args) throws InterruptedException{
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    sharedVariable = "hi";
                } catch (InterruptedException e) {
                }
            }
        }.start();

        System.out.println("开始时间：" + new Date());

        // 如果这里不加睡眠，发现始终无法退出循环，why?
        while (sharedVariable == null) {Thread.sleep(100);}
        System.out.println(sharedVariable);

        System.out.println("结束时间：" + new Date());
    }
}
