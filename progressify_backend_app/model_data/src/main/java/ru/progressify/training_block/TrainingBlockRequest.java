package ru.progressify.training_block;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrainingBlockRequest {

    private Integer num;
    private String name;
    private UUID eduId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TrainingBlockRequest that = (TrainingBlockRequest) o;
        return Objects.equals(num, that.num) && Objects.equals(name, that.name) && Objects.equals(eduId, that.eduId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name, eduId);
    }
}
