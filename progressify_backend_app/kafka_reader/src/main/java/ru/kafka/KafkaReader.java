package ru.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaReader {
    public static void main(String[] args) {
        SpringApplication.run(KafkaReader.class, args);
    }
}