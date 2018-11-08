package com.eddie;

import com.eddie.config.KafkaProducerConfig;
import com.eddie.entity.KafkaUrlNode;
import com.eddie.config.TypeDefine;
import com.eddie.entity.Message;
import com.eddie.kafka.KafkaFactory;
import com.eddie.utils.producer.EddieProducer;
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
        KafkaProducerConfig config = new KafkaProducerConfig();
        List<KafkaUrlNode> list = new ArrayList<>();
        list.add(new KafkaUrlNode("47.96.29.8", "9092"));
        config.setUrlNodeList(list);
        KafkaProducer producer = KafkaFactory.create()
                .init(config)
                .setKeyAndValueType(TypeDefine.STRING, TypeDefine.STRING)
                .build();
        EddieProducer eddieProducer = new EddieProducer("test", producer, true);
        eddieProducer.add(new Message("a", "b"));
        eddieProducer.run();
    }
}
