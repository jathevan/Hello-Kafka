package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerHello {
    public static void main(String[] args) {
        Properties props = new Properties();
        // Set the address of the Kafka server
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // Set the serializer class for keys
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // Set the serializer class for values
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

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