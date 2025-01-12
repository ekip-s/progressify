package ru.kafka.service;

import ru.model.models.kafka.KafkaEvent;


public interface KafkaEduService {

    void newLessonEventHandler(KafkaEvent event);
    void setLessonStatusHandler(KafkaEvent event);
}
