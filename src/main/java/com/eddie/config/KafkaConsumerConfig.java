package com.eddie.config;

import com.eddie.entity.KafkaUrlNode;
import com.eddie.exception.KafkaIllegalParameterException;

import java.util.List;
import java.util.Objects;

/**
 * @author eddie
 * @createTime 2018-11-09
 * @description 消费者配置
 */
public class KafkaConsumerConfig {
    /**
     * kafka url list
     * All nodes of a cluster
     */
    private List<KafkaUrlNode> urlNodeList;

    private String groupId;

    private String maxSize;

    private String maxIntervalMs;

    private String autoCommit;

    private String autoCommitMs;

    private String sessionTimeout;

    public String getUrlNodeList() {
        if (urlNodeList == null){
            throw new KafkaIllegalParameterException("缺少kafka服务消费地址");
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

    public String getGroupId() {
        if (Objects.isNull(groupId)){
            throw new KafkaIllegalParameterException("未设置[groupId]");
        }
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMaxSize() {
        if (Objects.isNull(maxSize)){
            return "10";
        }
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getMaxIntervalMs() {
        if (Objects.isNull(maxIntervalMs)){
            return "3000";
        }
        return maxIntervalMs;
    }

    public void setMaxIntervalMs(String maxIntervalMs) {
        this.maxIntervalMs = maxIntervalMs;
    }

    public String getAutoCommit() {
        if (Objects.isNull(autoCommit)){
            return "true";
        }
        return autoCommit;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public String getAutoCommitMs() {
        if (Objects.isNull(autoCommitMs)){
            return "1000";
        }
        return autoCommitMs;
    }

    public void setAutoCommitMs(String autoCommitMs) {
        this.autoCommitMs = autoCommitMs;
    }

    public String getSessionTimeout() {
        if (Objects.isNull(sessionTimeout)){
            return "30000";
        }
        return sessionTimeout;
    }

    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}
