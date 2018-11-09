package com.eddie.utils;

import com.eddie.utils.producer.EddieProducer;

/**
 * @author eddie
 * @createTime 2018-11-09
 * @description 终止线程操作
 */
public class ThreadShutdown {

    public static void finishThread(EddieProducer thread){
        try {
            Thread.sleep(5000);
            thread.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
