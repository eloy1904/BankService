package com.nttdata.bankservice.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String producerKeySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String producerValueSerializer;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String consumerKeyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String consumerValueDeserializer;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String consumerAutoOffsetReset;

    @Value("${spring.kafka.consumer.topic}")
    private String consumerTopic;

    // Configuración del Kafka Sender
    @Bean
    public KafkaSender<String, Object> reactiveKafkaSender() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put("bootstrap.servers", bootstrapServers);
        producerProps.put("key.serializer", producerKeySerializer);
        producerProps.put("value.serializer", producerValueSerializer);
        return KafkaSender.create(SenderOptions.create(producerProps));
    }

    // Configuración del Kafka Receiver
    @Bean
    public KafkaReceiver<String, String> reactiveKafkaReceiver() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put("bootstrap.servers", bootstrapServers);
        consumerProps.put("group.id", consumerGroupId);
        consumerProps.put("key.deserializer", consumerKeyDeserializer);
        consumerProps.put("value.deserializer", consumerValueDeserializer);
        consumerProps.put("auto.offset.reset", consumerAutoOffsetReset);

        ReceiverOptions<String, String> options = ReceiverOptions.<String, String>create(consumerProps)
                .subscription(List.of(consumerTopic)); // Suscribe al tópico especificado en properties
        return KafkaReceiver.create(options);
    }
}
