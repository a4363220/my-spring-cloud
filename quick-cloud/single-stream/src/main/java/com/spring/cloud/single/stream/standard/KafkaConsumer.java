package com.spring.cloud.single.stream.standard;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Collections;
import java.util.Properties;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/16 21:33
 */
public class KafkaConsumer extends Thread{

    public static final String topic = "test";

    private static org.apache.kafka.clients.consumer.KafkaConsumer kafkaConsumer =null;
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
        kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer(properties);

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
