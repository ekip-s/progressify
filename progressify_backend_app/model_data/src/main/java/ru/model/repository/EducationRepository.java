package ru.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.model.models.education.Education;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EducationRepository extends JpaRepository<Education, UUID> {

    List<Education> findAllByUserId(UUID userId);
    @Query("SELECT e FROM Education e JOIN e.blocks b JOIN b.lessons l WHERE l.id = :lessonId")
    Optional<Education> findByLesson(@Param("lessonId") UUID lessonId);
}
