package com.eddie.utils.producer;

import com.eddie.entity.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Collection;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description 生产者
 */
public class EddieProducer extends Thread{

    private Queue<Message> queue;

    private KafkaProducer<String, String> producer;

    private String topic;

    private boolean shutdown = false;

    public EddieProducer(final String topic, final KafkaProducer<String, String> producer){
        queue = new ConcurrentLinkedQueue<>();
        this.topic = topic;
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
        while (!shutdown && !Thread.currentThread().isInterrupted()) {
            if (!queue.isEmpty()) {
                long startTime = System.currentTimeMillis();
                Message message = Objects.requireNonNull(queue.poll());
                String key = message.getKey();
                String value = message.getValue();
                Future<RecordMetadata> send = producer.send(
                        new ProducerRecord<>(topic, key, value),
                        new ProducerCallBack(startTime, key, value)
                );
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel() {
        shutdown = true;
        Thread.currentThread().interrupt();
    }
}