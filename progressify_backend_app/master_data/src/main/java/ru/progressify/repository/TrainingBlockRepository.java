package ru.progressify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.progressify.model.training_block.TrainingBlock;

import java.util.UUID;

@Repository
public interface TrainingBlockRepository extends JpaRepository<TrainingBlock, UUID> {
}
