package com.spring.cloud.single.stream.standard;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 描述：
 *
 *    kafka发送者
 * @author 小谷
 * @Date 2020/1/16 21:31
 */
public class KafkaProducer {

    public static final String topic = "test";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 初始化配置
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.204.128:9092");// 集群地址
        // key序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        // value序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 设置当前客户端id
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"KafkaProducerDemo");
        // 0:消息发送给broker以后，不需要确认（性能高,数据可能丢失,）
        // 1:只需要获得Kafka集群中的leader确认即可返回
        // all(-1):需要ISR中所有的Replica进行确认(需要集群中所有节点进行确认,最安全,也可能存在数据丢失)
        properties.put(ProducerConfig.ACKS_CONFIG,"1");
        // 指定发送的分区，自定义(默认算法是哈希取模)
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
//                "com.kafka.kafkatest1.MyPartition");

        // 创建kafkaProducer
        org.apache.kafka.clients.producer.KafkaProducer<Integer,String> kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer<Integer, String>(properties);
        // 创建kafka消息 ProducerRecord
        String testMessage = "test小谷";
        ProducerRecord<Integer,String> record = new ProducerRecord<Integer,String>(topic,testMessage);

        // 发放 Kakfa 消息
        Future<RecordMetadata> metadataFuture = kafkaProducer.send(record);
        // 强制执行
        metadataFuture.get();
    }

}
