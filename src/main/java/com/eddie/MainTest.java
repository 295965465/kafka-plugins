package com.eddie;

import com.eddie.config.KafkaConsumerConfig;
import com.eddie.config.KafkaProducerConfig;
import com.eddie.config.TypeDefine;
import com.eddie.entity.KafkaUrlNode;
import com.eddie.entity.Message;
import com.eddie.kafka.KafkaFactory;
import com.eddie.utils.ThreadShutdown;
import com.eddie.utils.consumer.EddieConsumer;
import com.eddie.utils.producer.EddieProducer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description 测试类
 */
public class MainTest {

    public static void main(String[] args){

        List<KafkaUrlNode> list = new ArrayList<>();
        list.add(new KafkaUrlNode("47.96.29.8", "9092"));
        KafkaProducerConfig config = new KafkaProducerConfig();
        config.setUrlNodeList(list);
        KafkaProducer producer = KafkaFactory.create()
                .init(config)
                .setSerializerKeyAndValueType(TypeDefine.STRING, TypeDefine.STRING)
                .buildKafkaProducer();
        EddieProducer eddieProducer = new EddieProducer("test", producer);


        eddieProducer.start();
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.add(new Message("a", "b"));

        ThreadShutdown.finishThread(eddieProducer);
        System.out.println("测试");


        KafkaConsumerConfig conmuserConfig = new KafkaConsumerConfig();
        conmuserConfig.setUrlNodeList(list);
        conmuserConfig.setGroupId("test");
        KafkaConsumer consumer = KafkaFactory.create()
                .init(conmuserConfig)
                .setDeserializerKeyAndValueType(TypeDefine.DE_STRING, TypeDefine.DE_STRING)
                .buildKafkaConmuser();
        EddieConsumer eddieConsumer = new EddieConsumer("test", consumer);
        eddieConsumer.start();

    }
}
