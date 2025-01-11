package ru.progressify.training_block;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.progressify.StatusType;
import ru.progressify.lesson.LessonResponse;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrainingBlockResponse {

    private UUID id;
    private Integer num;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startAT;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endAT;
    private StatusType status;
    private List<LessonResponse> lessons;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TrainingBlockResponse that = (TrainingBlockResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
