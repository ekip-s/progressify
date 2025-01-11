package ru.progressify.kafka;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class KafkaEvent {

    private EventType eventType;
}
