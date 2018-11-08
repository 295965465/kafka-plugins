package com.eddie.exception;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description
 */
public class KafkaException extends RuntimeException {

    public KafkaException(String message) {
        super(message);
    }

    public KafkaException(String message, Throwable cause) {
        super(message, cause);
    }
}
