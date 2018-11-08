package com.eddie.kafka;

import com.eddie.config.KafkaProducerConfig;
import com.eddie.config.TypeDefine;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description kafka工厂对象生成High Level API
 */
public class KafkaFactory {

    private KafkaProducer<String, String> producer;
    private Properties props = new Properties();

    private KafkaFactory(){}

    public static KafkaFactory create(){
        return new KafkaFactory();
    }

    public KafkaFactory init(KafkaProducerConfig config){
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getUrlNodeList());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, config.getClientId());
        props.put(ProducerConfig.ACKS_CONFIG, config.getAck());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, config.getTimeout());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, config.getBatchSize());
        return this;
    }

    /**
     * 建议使用#{TypeDefine}方法，避免手动输入错误的序列化类
     * @param key
     * @param value
     * @return
     */
    @Deprecated
    public KafkaFactory setKeyAndValueType(Class key, Class value){
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value.getName());
        return this;
    }

    /**
     * 设置序列化方法
     * @param key
     * @param value
     * @return
     */
    public KafkaFactory setKeyAndValueType(TypeDefine key, TypeDefine value){
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key.getCls().getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value.getCls().getName());
        return this;
    }

    /**
     * 生产出KafkaProducer对象
     * @return
     */
    public KafkaProducer build(){
        this.producer = new KafkaProducer<>(props);
        return producer;
    }
}
