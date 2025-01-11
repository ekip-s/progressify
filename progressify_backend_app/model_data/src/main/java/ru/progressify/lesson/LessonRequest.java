package ru.progressify.lesson;


import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LessonRequest {

    private Integer num;
    private String name;
    private UUID blockId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LessonRequest that = (LessonRequest) o;
        return Objects.equals(num, that.num) && Objects.equals(name, that.name) && Objects.equals(blockId, that.blockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name, blockId);
    }
}
