package com.jx;

//import com.jx.service.manage.ResourceUpdateService;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 类功能描述：<br>
 *
 * kafka接收端
 *
 * @author jx
 * @version 5.0 since 2020-06-28
 */
@Component
@Slf4j
public class KafkaConsumer {

    public static final String TOPIC_JX_CNHD_TEST = "topic.jx.cnhd.dev";


    //分组
    public static final String TOPIC_JX_CNHD_GROUP1 = "topic.jx.cnhd.group1";
    public static final String TOPIC_JX_CNHD_GROUP2 = "topic.jx.cnhd.group2";


    @KafkaListener(topics = KafkaConsumer.TOPIC_JX_CNHD_TEST, groupId = KafkaConsumer.TOPIC_JX_CNHD_GROUP1)
    public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConsumer.TOPIC_JX_CNHD_TEST, groupId = KafkaConsumer.TOPIC_JX_CNHD_GROUP2)
    public void topic_production(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("topic_production 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}
