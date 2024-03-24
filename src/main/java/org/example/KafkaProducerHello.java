package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KafkaProducerHello {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/kafka.properties")) {
            // load a properties file
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a Kafka producer with the specified properties
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            // Create a record to send
            ProducerRecord<String, String> record = new ProducerRecord<>("hello-world-topic", "Hello, Kafka from Gradle and Java!");
            // Send the record
            producer.send(record);
        }

        System.out.println("Message sent!");
    }
}