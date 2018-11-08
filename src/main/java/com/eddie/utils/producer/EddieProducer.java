package com.eddie.utils.producer;

import com.eddie.entity.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Collection;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description 生产者
 */
public class EddieProducer extends Thread{

    private Queue<Message> queue;

    private KafkaProducer<String, String> producer;

    private String topic;

    private boolean isAsync;

    public EddieProducer(final String topic, final KafkaProducer<String, String> producer, final boolean isAsync){
        queue = new ConcurrentLinkedQueue<>();
        this.topic = topic;
        this.isAsync = isAsync;
        this.producer = producer;
    }

    public boolean add(Message e){
        return queue.add(e);
    }

    public boolean add(Collection<? extends Message> collection){
        return queue.addAll(collection);
    }

    @Override
    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
                long startTime = System.currentTimeMillis();
                Message message = Objects.requireNonNull(queue.poll());
                String key = message.getKey();
                String value = message.getValue();
                if (isAsync) {
                    producer.send(
                            new ProducerRecord<>(topic, key, value),
                            new ProducerCallBack(startTime, key, value)
                    );
                } else {
                    try {
                        producer.send(
                                new ProducerRecord<>(topic, key, value)
                                , new ProducerCallBack(startTime, key, value)
                        ).get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}