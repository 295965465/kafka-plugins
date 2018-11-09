package com.eddie.kafka;

import com.eddie.config.KafkaConsumerConfig;
import com.eddie.config.KafkaProducerConfig;
import com.eddie.config.TypeDefine;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
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
    private KafkaConsumer<Integer, String> consumer;
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

    public KafkaFactory init(KafkaConsumerConfig config){
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getUrlNodeList());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, config.getGroupId());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, config.getMaxSize());
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, config.getMaxIntervalMs());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, config.getAutoCommit());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, config.getAutoCommitMs());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, config.getSessionTimeout());
        return this;
    }

    /**
     * 建议使用#{TypeDefine}方法，避免手动输入错误的序列化类
     * @param key
     * @param value
     * @return
     */
    @Deprecated
    public KafkaFactory setSerializerKeyAndValueType(Class key, Class value){
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value.getName());
        return this;
    }

    /**
     * 建议使用#{TypeDefine}方法，避免手动输入错误的序列化类
     * @param key
     * @param value
     * @return
     */
    @Deprecated
    public KafkaFactory setDeserializerKeyAndValueType(Class key, Class value){
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, value.getName());
        return this;
    }


    /**
     * 设置序列化方法
     * @param key
     * @param value
     * @return
     */
    public KafkaFactory setSerializerKeyAndValueType(TypeDefine key, TypeDefine value){
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key.getCls().getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value.getCls().getName());
        return this;
    }

    public KafkaFactory setDeserializerKeyAndValueType(TypeDefine key, TypeDefine value){
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key.getCls().getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, value.getCls().getName());
        return this;
    }

    /**
     * 生产出KafkaProducer对象
     * @return
     */
    public KafkaProducer buildKafkaProducer(){
        this.producer = new KafkaProducer<>(props);
        return producer;
    }
    /**
     * 生产出KafkaConmuser对象
     * @return
     */
    public KafkaConsumer buildKafkaConmuser(){
        this.consumer = new KafkaConsumer<>(props);
        return consumer;
    }
}
