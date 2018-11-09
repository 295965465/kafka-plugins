package com.eddie.config;

import com.eddie.entity.KafkaUrlNode;

import java.util.List;

/**
 * @author eddie
 * @createTime 2018-11-09
 * @description 消费者配置
 */
public class KafkaConmuserConfig {
    /**
     * kafka url list
     * All nodes of a cluster
     */
    private List<KafkaUrlNode> urlNodeList;

}
