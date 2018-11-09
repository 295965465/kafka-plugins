package com.eddie.utils.consumer;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.time.Duration;
import java.util.Collections;


/**
 * @author eddie
 * @createTime 2018-11-08
 * @description
 */
public class EddieConsumer extends ShutdownableThread {

    private String topic;

    private KafkaConsumer consumer;

    public EddieConsumer(final String topic, final KafkaConsumer<String, String> producer){
        super("KafkaConsumerExample", false);
        this.topic = topic;
        this.consumer = producer;
    }


    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(3));
        int count = records.count();
        System.out.println( "测试结果" + count);
        for (ConsumerRecord<Integer, String> record : records) {
            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
        }
    }

    @Override
    public String name() {
        return "KafkaConsumerExample";
    }

    @Override
    public boolean isInterruptible() {
        return Thread.currentThread().isInterrupted();
    }
}
