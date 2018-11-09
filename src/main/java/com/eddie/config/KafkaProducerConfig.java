package com.eddie.config;

import com.eddie.entity.KafkaUrlNode;
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
    private List<KafkaUrlNode> urlNodeList;

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

    public String getUrlNodeList() {
        if (urlNodeList == null){
            throw new KafkaIllegalParameterException("缺少kafka服务URL地址");
        }
        StringBuilder builder = new StringBuilder();
        for (KafkaUrlNode node:urlNodeList){
            builder.append(node.getUrl())
                    .append(":")
                    .append(node.getPort())
            .append(",");
        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }

    public void setUrlNodeList(List<KafkaUrlNode> urlNodeList) {
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

}
