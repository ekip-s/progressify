package ru.model.models.education;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.model.models.StatusType;
import ru.model.models.training_block.TrainingBlock;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="education")
public class Education {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime createdAT;
    @Column(name = "start_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startAT;
    @Column(name = "end_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endAT;
    @Enumerated(EnumType.ORDINAL)
    private StatusType status;
    @Column(name = "total")
    private Integer total;
    @Column(name = "done_edu")
    private Integer doneEdu;
    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingBlock> blocks;

    public Education createNewEdu(UUID userId) {
        this.userId = userId;
        this.createdAT = LocalDateTime.now();
        this.status = StatusType.NEW;
        this.total = 0;
        this.doneEdu = 0;
        return this;
    }

    public void newLesson() {
        this.total += 1;
    }

    public void doneLesson() {
        this.doneEdu += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(id, education.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
