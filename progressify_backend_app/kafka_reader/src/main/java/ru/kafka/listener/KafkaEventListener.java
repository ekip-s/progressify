package ru.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;
import ru.progressify.kafka.KafkaEvent;

@Service
public class KafkaEventListener {

    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaEventListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "progressify_topic", groupId = "java")
    public void listenEvent(String message) {
        KafkaEvent event = parseMessage(message);

        switch (event.getEventType()) {
            case NEW_LESSON:
                System.out.println(event);
                break;
            default:
                handleUnknownEvent(event);
        }
    }

    private KafkaEvent parseMessage(String message) {
        try {
            System.out.println("Сообщение: " + message);
            return objectMapper.readValue(message, KafkaEvent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при разборе сообщения", e);
        }
    }

    private void handleUnknownEvent(KafkaEvent event) {
        System.out.println("Неизвестное событие: " + event);
    }
}
