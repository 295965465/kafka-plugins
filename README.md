# kafka-plugins

工具提供了快速使用的方法

生产者：
```
List<KafkaUrlNode> list = new ArrayList<>();
list.add(new KafkaUrlNode("47.96.29.8", "9092"));
KafkaProducerConfig config = new KafkaProducerConfig();
config.setUrlNodeList(list);
KafkaProducer producer = KafkaFactory.create()
        .init(config)
        .setSerializerKeyAndValueType(TypeDefine.STRING, TypeDefine.STRING)
        .buildKafkaProducer();
EddieProducer eddieProducer = new EddieProducer("test", producer);
eddieProducer.start();
```
这样生产者线程就开始工作了，添加数据使用``add``方法
```
eddieProducer.add(new Message("a", "b"));
eddieProducer.add(new Message("a", "b"));
eddieProducer.add(new Message("a", "b"));
eddieProducer.add(new Message("a", "b"));
eddieProducer.add(new Message("a", "b"));
```
目前该方案仅支持添加Message对象，可以Message里的类型属性，
key / value 
注意，如果更改key和value的属性，要修改序列化和反序列化的类型，
这里建议使用``TypeDefine``枚举类型中的值，也可以使用``CommonTypeDefine``中的参数

---
消费者：
```
List<KafkaUrlNode> list = new ArrayList<>();
list.add(new KafkaUrlNode("47.96.29.8", "9092"));
KafkaConsumerConfig conmuserConfig = new KafkaConsumerConfig();
conmuserConfig.setUrlNodeList(list);
conmuserConfig.setGroupId("test");
KafkaConsumer consumer = KafkaFactory.create()
        .init(conmuserConfig)
        .setDeserializerKeyAndValueType(TypeDefine.DE_STRING, TypeDefine.DE_STRING)
        .buildKafkaConmuser();
EddieConsumer eddieConsumer = new EddieConsumer("test", consumer);
eddieConsumer.start();
```
启动即可开始消费topic为test，组为test的空间

----

最后，更多配置看``KafkaProducerConfig``和``KafkaConsumerConfig``.
如果不想了解太多，我内置的默认参数也是可以满足日常测试需要的

* 注意不要拿到生产中使用，没对Queue以及并发情况，进程关闭等做任何测试，，该工具仅可满足日常测试
