package ru.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.model.models.training_block.TrainingBlock;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingBlockRepository extends JpaRepository<TrainingBlock, UUID> {

    @Query("SELECT tb FROM TrainingBlock tb JOIN tb.lessons l WHERE l.id = :lessonId")
    Optional<TrainingBlock> getBlockByLesson(@Param("lessonId") UUID lessonId);
}
