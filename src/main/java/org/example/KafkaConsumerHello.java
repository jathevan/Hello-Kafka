package org.example;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerHello {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/kafka.properties")) {
            // load a properties file
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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