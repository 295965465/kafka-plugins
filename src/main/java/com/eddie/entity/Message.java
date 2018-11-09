package com.eddie.entity;

import lombok.Data;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description 消息
 */
@Data
public class Message {

    private String key;

    private String value;

    public Message(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
