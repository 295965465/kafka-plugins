package com.eddie.utils.producer;

import com.eddie.config.KafkaProducerConfig;
import com.eddie.config.TypeDefine;
import com.eddie.entity.KafkaUrlNode;
import com.eddie.entity.Message;
import com.eddie.kafka.KafkaFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EddieProducerTest {

    @Test
    public void run() throws InterruptedException {
        KafkaProducerConfig config = new KafkaProducerConfig();
        List<KafkaUrlNode> list = new ArrayList<>();
        list.add(new KafkaUrlNode("47.96.29.8", "9092"));
        config.setUrlNodeList(list);
        KafkaProducer producer = KafkaFactory.create()
                .init(config)
                .setKeyAndValueType(TypeDefine.STRING, TypeDefine.STRING)
                .build();
        EddieProducer eddieProducer = new EddieProducer("test", producer);

        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.start();
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));




        //eddieProducer.interrupt();
    }
}