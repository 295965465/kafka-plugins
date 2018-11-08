package com.eddie.config;

import lombok.Data;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description kafka url node
 */
@Data
public class KafakaUrlNode {

    /**
     * kafka server url
     */
    private String url;
    /**
     * kafka server port; default 9092
     */
    private String port;
}
