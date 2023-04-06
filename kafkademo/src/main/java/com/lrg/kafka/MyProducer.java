package com.lrg.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //1.设置kafka集群
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        //2.设置ack级别
        props.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
        //3.设置重试次数
        props.setProperty(ProducerConfig.RETRIES_CONFIG, "1");
        //4.设置main线程批量发送RecordAccumulator的内存大小、一个批次数据大于该数值将会发送
        props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        //5.设置若未达到批次发送内存大小，等待linger.ms时间长度后发送
        props.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1");
        //6.设置共享变量RecordAccumulator内存大小
        props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        //7.设置序列化器序列key的类
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //7.设置序列化器序列value的类
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //8.创建生产者
        KafkaProducer producer = new KafkaProducer(props);
        for (int i = 0; i < 10; i++) {
            //9.发送数据
            //ProducerRecord 存在三种构造方法，对应producer的三种分配策略
            producer.send(new ProducerRecord("first", "kafkademo----" + i));
        }
        //10.关闭资源，若未关闭资源producer.send()的数据将不会发送。
        producer.close();
    }
}
