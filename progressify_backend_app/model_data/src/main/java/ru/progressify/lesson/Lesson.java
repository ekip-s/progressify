package ru.progressify.lesson;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.progressify.StatusType;
import ru.progressify.training_block.TrainingBlock;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="lesson")
public class Lesson {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "num")
    private Integer num;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "block_id")
    private TrainingBlock block;
    @Column(name = "start_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startAT;
    @Column(name = "end_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endAT;
    @Enumerated(EnumType.ORDINAL)
    private StatusType status;

    public Lesson(LessonRequest lessonRequest, TrainingBlock block) {
        this.num = lessonRequest.getNum();
        this.name = lessonRequest.getName();
        this.block = block;
        this.status = StatusType.NEW;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
