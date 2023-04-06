package com.lrg.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class MyConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //����kafka��Ⱥ
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        //����key�����л���
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //����value�����л���
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //����consumer offset�Ƿ��Զ��ύ��Ĭ���Զ��ύ
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //����offset�Զ��ύʱ��������enable.auto.commit����Ϊfalse���ò�������Ч
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "0.1");
        //����offset�������õĲ��ԣ���earliest��latest��none  Ĭ��latest
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        //�����������ȡkafka�ڲ���topic������_consumer_offsets
        props.put(ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG, "true");
        //����consumer group
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka_demo");

        //����������
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //����topics,�����ü���
        consumer.subscribe(Arrays.asList("first", "second"), new ConsumerRebalanceListener() {
            //�ٷ���ǰ����
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                for (TopicPartition partition : partitions) {
                    long offset = consumer.position(partition);
                }
            }

            //��������
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });
        //�������ݣ�����Ϊδ��ȡ����ʱ�ȴ�һ��ʱ����ȥ��ȡ
        ConsumerRecords<String, String> poll = consumer.poll(100);
        for (ConsumerRecord<String, String> consumerRecord : poll) {
            System.out.println(consumerRecord.value());
        }
        //�첽�ύoffset����ʧ�ܽ�����
        consumer.commitSync();
        //ͬ���ύoffset����ʧ�ܽ���������
        //consumer.commitAsync();
    }
}
