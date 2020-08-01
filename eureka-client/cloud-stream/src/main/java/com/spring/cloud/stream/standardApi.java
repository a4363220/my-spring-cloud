package com.spring.cloud.stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 描述：
 *    kafka标准api
 * @author 小谷
 * @Date 2020/1/15 10:45
 */
public class standardApi extends Thread{

    public static final String topic = "test";


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        producer();

        // 消费：
        //new standardApi().start();

        // 第一步，找到当前consumer group的offset维护在那个分区中 取模对应的分区数
        //System.out.println(("kafkaConsumeDemo".hashCode())%3);
    }


    /**
     * kafka标准发送消息api
     * */
    public static void producer() throws ExecutionException, InterruptedException {
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
        KafkaProducer<Integer,String> kafkaProducer = new KafkaProducer<Integer, String>(properties);
        // 创建kafka消息 ProducerRecord
        String testMessage = "test小谷";
        ProducerRecord<Integer,String> record = new ProducerRecord<Integer,String>(topic,testMessage);

        // 发放 Kakfa 消息
        Future<RecordMetadata> metadataFuture = kafkaProducer.send(record);
        // 强制执行
        metadataFuture.get();

    }

    private static  KafkaConsumer kafkaConsumer =null;
    /**
     * kafka 消费者
     * */
    public void consumer(){
            Properties properties = new Properties();

            //properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.202.128:9092,192.168.202.132:9092,192.168.202.130:9092");
            properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.20.10.161:9092");
            // 分组很重要
            properties.put(ConsumerConfig.GROUP_ID_CONFIG,"payGroup");
            // 自动确认，如果设置为false那么就没有自动提交不会有offset
            properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
            // 自动确认的间隔时间，中间带多条消息也会被一起确认
            properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
            // key反序列化
            properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer");
            // value反序列化
            properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
            // 设置偏移,对于新的groupid如果设置为earliest来说，那么他会从最早的消息开始消费
            // earliest：对于新的groupid来说，重置offset
            // latest：对于新的roupid来说，直接取已经消费并且提交的offset
            // none：每个新创建的groupid都会创建一个offset，如果设置为none表示没有offset，启动会报出异常
            properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
            kafkaConsumer = new KafkaConsumer(properties);

            // 订阅
            kafkaConsumer.subscribe(Collections.singletonList(topic));

            // 指定消费分区
            //TopicPartition topicPartition = new TopicPartition(topicc,0);
            //kafkaConsumer.assign(Arrays.asList(topicPartition));
        }


    @Override
    public void run() {
        while(true){
            // 拉消息，超时时间，可以通过poll消息的消费数通过max...,减少poll的间隔次数
            ConsumerRecords<Integer,String> consumerRecord = kafkaConsumer.poll(1000);


            for(ConsumerRecord record:consumerRecord){
                System.out.println("message receiver："+record.value()+" 分区："+record.partition());
                //没有自动确认,就需要手动确认，有同步异步，和带回调的区别
                //kafkaConsumer.commitAsync();

            }
        }
    }


}
