package ru.model.models.education;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.model.models.StatusType;
import ru.model.models.training_block.TrainingBlockResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponse {

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAT;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startAT;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endAT;
    private StatusType status;
    private Integer total;
    private Integer doneEdu;
    private List<TrainingBlockResponse> blocks;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EducationResponse that = (EducationResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
