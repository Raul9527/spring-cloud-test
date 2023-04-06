package com.lrg.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //1.����kafka��Ⱥ
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        //2.����ack����
        props.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
        //3.�������Դ���
        props.setProperty(ProducerConfig.RETRIES_CONFIG, "1");
        //4.����main�߳���������RecordAccumulator���ڴ��С��һ���������ݴ��ڸ���ֵ���ᷢ��
        props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        //5.������δ�ﵽ���η����ڴ��С���ȴ�linger.msʱ�䳤�Ⱥ���
        props.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1");
        //6.���ù������RecordAccumulator�ڴ��С
        props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        //7.�������л�������key����
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //7.�������л�������value����
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //8.����������
        KafkaProducer producer = new KafkaProducer(props);
        for (int i = 0; i < 10; i++) {
            //9.��������
            //ProducerRecord �������ֹ��췽������Ӧproducer�����ַ������
            producer.send(new ProducerRecord("first", "kafkademo----" + i));
        }
        //10.�ر���Դ����δ�ر���Դproducer.send()�����ݽ����ᷢ�͡�
        producer.close();
    }
}
