package com.eddie.config;

import org.apache.kafka.common.serialization.*;

/**
 * @author eddie
 * @createTime 2018-11-08
 * @description 支持的类型定义
 */
public class CommonTypeDefine {

    public static final Class STRING = StringSerializer.class;

    public static final Class BYTE = ByteArraySerializer.class;

    public static final Class INTEGER = IntegerSerializer.class;

    public static final Class LONG = LongSerializer.class;

    public static final Class SHORT = ShortSerializer.class;

    public static final Class FLOAT = FloatSerializer.class;

    public static final Class DOUBLE = DoubleSerializer.class;


    public static final Class DE_STRING = StringDeserializer.class;

    public static final Class DE_BYTE = ByteArrayDeserializer.class;

    public static final Class DE_INTEGER = IntegerDeserializer.class;

    public static final Class DE_LONG = LongDeserializer.class;

    public static final Class DE_SHORT = ShortDeserializer.class;

    public static final Class DE_FLOAT = FloatDeserializer.class;

    public static final Class DE_DOUBLE = DoubleDeserializer.class;

}
