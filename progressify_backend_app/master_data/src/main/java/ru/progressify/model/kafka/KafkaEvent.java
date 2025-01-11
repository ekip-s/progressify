package ru.progressify.model.kafka;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class KafkaEvent {

    @NotNull(message = "Тип события обязателен")
    private EventType eventType;
}
