package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerHello {
    public static void main(String[] args) {
        Properties props = new Properties();
        // Set the address of the Kafka server
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // Set the consumer group ID
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // Set the deserializer class for keys
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // Set the deserializer class for values
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // Set the offset reset policy
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        // Create a Kafka consumer with the specified properties
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // Subscribe to the specified topic
            consumer.subscribe(Collections.singletonList("hello-world-topic"));

            // Continuously poll for new records
            while (true) {
                try {

                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    // Print each received record
                    records.forEach(record -> System.out.println("Received: " + record.value()));
                } catch (Exception e) {
                    System.err.println( "Error processing message: " + e.getMessage());
                }
            }
        }
    }
}