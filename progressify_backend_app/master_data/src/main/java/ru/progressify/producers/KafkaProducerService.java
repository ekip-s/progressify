package ru.progressify.producers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.progressify.model.kafka.KafkaEvent;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaEvent event) {
        kafkaTemplate.send(topic, event);
        log.info("Kafka: KafkaProducerService sendMessage, событие: {}", event);
    }
}
