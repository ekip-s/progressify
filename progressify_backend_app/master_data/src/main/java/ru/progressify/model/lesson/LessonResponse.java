package ru.progressify.model.lesson;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.progressify.model.StatusType;

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
