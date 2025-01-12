package ru.model.models.kafka;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.model.models.StatusType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KafkaEvent {

    private EventType eventType;
    private UUID lessonId;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime eventAT;
    private StatusType newStatus;

    public KafkaEvent(EventType eventType, UUID lessonId) {
        this.eventType = eventType;
        this.lessonId = lessonId;
        this.eventAT =  LocalDateTime.now();
    }

    public KafkaEvent(EventType eventType, UUID lessonId, StatusType newStatus) {
        this.eventType = eventType;
        this.lessonId = lessonId;
        this.eventAT = LocalDateTime.now();
        this.newStatus = newStatus;
    }
}
