package ru.progressify.model.training_block;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.progressify.model.StatusType;
import ru.progressify.model.education.Education;
import ru.progressify.model.lesson.Lesson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="training_block")
public class TrainingBlock {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "num")
    private Integer num;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "edu_id")
    private Education education;
    @Column(name = "start_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startAT;
    @Column(name = "end_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endAT;
    @Enumerated(EnumType.ORDINAL)
    private StatusType status;
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();

    public TrainingBlock(Education education, TrainingBlockRequest trainingBlockRequest) {
        this.num = trainingBlockRequest.getNum();
        this.name = trainingBlockRequest.getName();
        this.education = education;
        this.status = StatusType.NEW;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TrainingBlock that = (TrainingBlock) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


