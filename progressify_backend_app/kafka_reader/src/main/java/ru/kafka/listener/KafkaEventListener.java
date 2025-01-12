package ru.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;
import ru.kafka.service.KafkaEduService;
import ru.model.models.kafka.KafkaEvent;

@Slf4j
@Service
public class KafkaEventListener {

    private final ObjectMapper objectMapper;
    private final KafkaEduService kafkaEduService;

    @Autowired
    public KafkaEventListener(ObjectMapper objectMapper, KafkaEduService kafkaEduService) {
        this.objectMapper = objectMapper;
        this.kafkaEduService = kafkaEduService;
    }

    @KafkaListener(topics = "progressify_topic", groupId = "java")
    public void listenEvent(String message) {
        KafkaEvent event = parseMessage(message);

        log.info("EVENT KafkaEventListener: {}", event);
        switch (event.getEventType()) {
            case NEW_LESSON:
                kafkaEduService.newLessonEventHandler(event);
                break;
            case SET_STATUS:
                kafkaEduService.setLessonStatusHandler(event);
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
