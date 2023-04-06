package com.lrg.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class MyConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //设置kafka集群
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        //设置key反序列化类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //设置value反序列化类
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //设置consumer offset是否自动提交，默认自动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //设置offset自动提交时间间隔，若enable.auto.commit设置为false，该参数不生效
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "0.1");
        //设置offset重新设置的策略，有earliest、latest、none  默认latest
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        //设置是允许读取kafka内部的topic，比如_consumer_offsets
        props.put(ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG, "true");
        //设置consumer group
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka_demo");

        //创建消费者
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //订阅topics,并设置监听
        consumer.subscribe(Arrays.asList("first", "second"), new ConsumerRebalanceListener() {
            //再分配前调用
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                for (TopicPartition partition : partitions) {
                    long offset = consumer.position(partition);
                }
            }

            //分配后调用
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });
        //拉起数据，参数为未拉取数据时等待一段时间再去拉取
        ConsumerRecords<String, String> poll = consumer.poll(100);
        for (ConsumerRecord<String, String> consumerRecord : poll) {
            System.out.println(consumerRecord.value());
        }
        //异步提交offset，若失败将重试
        consumer.commitSync();
        //同步提交offset，若失败将不会重试
        //consumer.commitAsync();
    }
}
