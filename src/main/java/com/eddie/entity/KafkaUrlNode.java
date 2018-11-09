package com.eddie.entity;

import lombok.Data;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description kafka url node
 */
@Data
public class KafkaUrlNode {

    /**
     * kafka server url
     */
    private String url;
    /**
     * kafka server port; default 9092
     */
    private String port;

    public KafkaUrlNode(String url, String port) {
        this.url = url;
        this.port = port;
    }
}
