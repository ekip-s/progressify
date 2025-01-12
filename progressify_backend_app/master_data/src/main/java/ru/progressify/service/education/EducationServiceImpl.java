package ru.progressify.service.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exception.ConflictException;
import ru.exception.NotFoundException;
import ru.model.models.StatusType;
import ru.model.models.education.Education;
import ru.model.models.education.EducationListResponse;
import ru.model.models.education.EducationRequest;
import ru.model.models.education.EducationResponse;
import ru.model.models.lesson.Lesson;
import ru.model.models.training_block.TrainingBlock;
import ru.model.repository.TrainingBlockRepository;
import ru.progressify.mapper.EducationMapper;
import ru.progressify.producers.KafkaProducerService;
import ru.model.repository.EducationRepository;
import ru.progressify.service.TokenService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class EducationServiceImpl implements EducationService{

    private final TokenService tokenService;
    private final EducationMapper educationMapper;
    private final EducationRepository educationRepository;
    private final TrainingBlockRepository trainingBlockRepository;


    @Autowired
    public EducationServiceImpl(TokenService tokenService, EducationMapper educationMapper, EducationRepository educationRepository, KafkaProducerService kafkaProducerService, TrainingBlockRepository trainingBlockRepository) {
        this.tokenService = tokenService;
        this.educationMapper = educationMapper;
        this.educationRepository = educationRepository;
        this.trainingBlockRepository = trainingBlockRepository;
    }

    @Override
    @Transactional
    public UUID createNewEdu(EducationRequest educationRequest) {
        Education education = educationMapper.toEdu(educationRequest);
        education.createNewEdu(tokenService.getCurrentUserId());
        return educationRepository.save(education).getId();
    }

    @Override
    public List<EducationListResponse> getEduByClientId() {
        UUID userId = tokenService.getCurrentUserId();
        return educationMapper.toResponseList(educationRepository.findAllByUserId(userId));
    }

    @Override
    public EducationResponse getEduByEduId(UUID eduId) {

        Education education = getEduByEduIdLocal(eduId);
        if(education.getUserId().equals(tokenService.getCurrentUserId())) {
            return educationMapper.toResponse(education);
        } else {
            throw new ConflictException("Задача принадлежит другому пользователю.", "Конфликт");
        }
    }

    @Override
    public Education getEduByEduIdLocal(UUID eduId) {
        return educationRepository.findById(eduId).orElseThrow(() -> new NotFoundException(
                "Нет обучения с указанным id", "Нет данных"));
    }

    @Override
    @Transactional
    public void deleteEduByEduId(UUID eduId) {
        educationRepository.deleteById(eduId);
    }

    @Override
    @Transactional
    public void setAllEdu() {
        List<Education> educations = educationRepository.findAll();

        for(Education edu: educations) {
            LocalDateTime eduStartDate = null;
            LocalDateTime eduEndDate = null;

            int lessonCount = 0;
            int lessonDoneCount = 0;
            int lessonInProgressCount = 0;

            List<TrainingBlock> blocks = edu.getBlocks();

            for (TrainingBlock block: blocks) {
                System.out.println("blocks" + blocks);
                List<Lesson> lessons = block.getLessons();



                if (!lessons.isEmpty()) {
                    System.out.println("lessons, обновленный код " + lessons);
                    Optional<LocalDateTime> blockStartDate = lessons
                            .stream()
                            .map(Lesson::getStartAT)
                            .filter(Objects::nonNull)
                            .min(LocalDateTime::compareTo);
                    Optional<LocalDateTime> blockEndDate = lessons
                            .stream()
                            .map(Lesson::getEndAT)
                            .filter(Objects::nonNull)
                            .max(LocalDateTime::compareTo);
                    System.out.println("blockStartDate " + blockStartDate);
                    System.out.println("blockEndDate" + blockEndDate);

                    int lessonSize = lessons.size();
                    int lessonDoneSize = lessons
                            .stream()
                            .filter(l -> l.getStatus() == StatusType.DONE)
                            .toList()
                            .size();
                    int lessonInProgressSize = lessons
                            .stream()
                            .filter(l -> l.getStatus() == StatusType.IN_PROGRESS)
                            .toList()
                            .size();
                    lessonCount += lessonSize;
                    lessonDoneCount += lessonDoneSize;
                    lessonInProgressCount += lessonInProgressSize;

                    if(lessonSize != 0 && lessonDoneSize == lessonSize) {

                        if (blockStartDate.isPresent() && (eduStartDate == null || blockStartDate.get().isBefore(eduStartDate))) {
                            eduStartDate = blockStartDate.get();
                        }

                        if (blockEndDate.isPresent() && (eduEndDate == null || blockEndDate.get().isAfter(eduEndDate))) {
                            eduEndDate = blockEndDate.get();
                        }

                        block.setStatus(StatusType.DONE);
                        block.setStartAT(blockStartDate.get());
                        block.setEndAT(blockEndDate.get());
                    }
                    if (lessonInProgressSize > 0) {

                        if (blockStartDate.isPresent() && (eduStartDate == null || blockStartDate.get().isBefore(eduStartDate))) {
                            eduStartDate = blockStartDate.get();
                        }

                        block.setStatus(StatusType.IN_PROGRESS);
                        block.setStartAT(blockStartDate.get());
                    }
                    if (!block.getStatus().equals(StatusType.NEW)) {
                        trainingBlockRepository.save(block);
                    }
                }
            }

            if (lessonCount != 0 && lessonCount == lessonDoneCount) {
                edu.setStatus(StatusType.DONE);
                edu.setStartAT(eduStartDate);
                edu.setEndAT(eduEndDate);
            }

            if(lessonCount != 0 && lessonInProgressCount > 0) {
                edu.setStatus(StatusType.IN_PROGRESS);
                edu.setStartAT(eduStartDate);
            }
            edu.setTotal(lessonCount);
            edu.setDoneEdu(lessonDoneCount);
            educationRepository.save(edu);

        }
    }
}