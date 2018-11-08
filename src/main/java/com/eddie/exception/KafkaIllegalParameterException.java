package com.eddie.exception;

import com.eddie.exception.KafkaException;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description 非法参数异常
 */
public class KafkaIllegalParameterException extends KafkaException {

    public KafkaIllegalParameterException(String message) {
        super(message);
    }

    public KafkaIllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

}
