package ru.model.models.lesson;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.model.models.StatusType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {

    private UUID id;
    private Integer num;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startAT;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endAT;
    private StatusType status;
}
