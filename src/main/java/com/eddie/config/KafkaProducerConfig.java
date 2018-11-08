package com.eddie.config;

import com.eddie.exception.KafkaIllegalParameterException;

import java.util.List;
import java.util.Objects;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description kafka配置实体对象
 */
public class KafkaProducerConfig {

    /**
     * kafka url list
     * All nodes of a cluster
     */
    private List<KafakaUrlNode> urlNodeList;

    /**
     * An id string to pass to the server when making requests.
     * The purpose of this is to be able to track the source of requests beyond just ip/port by allowing a logical application name to be included in server-side request logging.
     */
    private String clientId;

    /**
     * This should be larger than replica.lag.time.max.ms (a broker configuration)
     * to reduce the possibility of message duplication due to unnecessary producer retries.
     */
    private String timeout;

    private String ack;

    /**
     * when data size arrive to batchSize
     * kafka will send those message to server immediately
     */
    private String batchSize;

    /**
     * topic
     */
    private String topic;

    private String async;

    public String getUrlNodeList() {
        StringBuilder builder = new StringBuilder();
        for (KafakaUrlNode node:urlNodeList){
            builder.append(node.getUrl())
                    .append(":")
                    .append(node.getPort())
            .append(",");
        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }

    public void setUrlNodeList(List<KafakaUrlNode> urlNodeList) {
        this.urlNodeList = urlNodeList;
    }

    public String getClientId() {
        if (Objects.isNull(clientId)) {
            return "DEFAULT";
        }
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTimeout() {
        if (Objects.isNull(timeout)) {
            return "10000";
        }
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getAck() {
        if (Objects.isNull(ack)) {
            return "1";
        }
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getBatchSize() {
        if (Objects.isNull(batchSize)){
            return "2000";
        }
        return batchSize;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }

    public String getTopic() {
        if (Objects.isNull(topic)){
            throw new KafkaIllegalParameterException("主题参数未设置");
        }
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAsync() {
        if (Objects.isNull(async)){
            return "true";
        }
        return async;
    }

    public void setAsync(String async) {
        this.async = async;
    }
}
