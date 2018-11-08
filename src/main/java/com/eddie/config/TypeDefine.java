package com.eddie.config;

import org.apache.kafka.common.serialization.*;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description
 */
public enum TypeDefine {
    /**
     *  序列化
     */
    STRING(StringSerializer.class),
    BYTE(ByteArraySerializer.class),
    INTEGER(IntegerSerializer.class),
    LONG(LongSerializer.class),
    SHORT(ShortSerializer.class),
    FLOAT(FloatSerializer.class),
    DOUBLE(DoubleSerializer.class),
    /**
     * 反序列化
     */
    DE_STRING(StringDeserializer.class),
    DE_BYTE(ByteArrayDeserializer.class),
    DE_INTEGER(IntegerDeserializer.class),
    DE_LONG(LongDeserializer.class),
    DE_SHORT(ShortDeserializer.class),
    DE_FLOAT(FloatDeserializer.class),
    DE_DOUBLE(DoubleDeserializer.class);

    private Class cls;

    private TypeDefine(Class cls) {
        this.cls = cls;
    }

    public Class getCls() {
        return cls;
    }
}
