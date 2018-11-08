package com.eddie.utils.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description 回调函数
 */
public class ProducerCallBack implements Callback {
    private final long startTime;
    private final String key;
    private final String message;

    public ProducerCallBack(long startTime, String key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.printf("message(%s, %s) sent to partition(%d), offset(%d) in %d ms%n",
                    key, message, metadata.partition(), metadata.offset(), elapsedTime);
        } else {
            exception.printStackTrace();
        }
    }
}
