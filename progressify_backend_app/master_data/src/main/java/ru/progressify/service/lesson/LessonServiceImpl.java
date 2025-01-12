package ru.progressify.service.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exception.ConflictException;
import ru.exception.NotFoundException;
import ru.model.models.StatusType;
import ru.model.models.kafka.EventType;
import ru.model.models.kafka.KafkaEvent;
import ru.model.models.lesson.Lesson;
import ru.model.models.lesson.LessonRequest;
import ru.model.models.lesson.LessonResponse;
import ru.progressify.mapper.LessonMapper;
import ru.progressify.producers.KafkaProducerService;
import ru.model.repository.LessonRepository;
import ru.progressify.service.TokenService;
import ru.progressify.service.training_block.TrainingBlockService;
import ru.model.models.training_block.TrainingBlock;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final TrainingBlockService trainingBlockService;
    private final KafkaProducerService kafkaProducerService;
    private final LessonMapper lessonMapper;
    private final TokenService tokenService;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, TrainingBlockService trainingBlockService, KafkaProducerService kafkaProducerService, LessonMapper lessonMapper, TokenService tokenService) {
        this.lessonRepository = lessonRepository;
        this.trainingBlockService = trainingBlockService;
        this.kafkaProducerService = kafkaProducerService;
        this.lessonMapper = lessonMapper;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public LessonResponse addNewLesson(LessonRequest lessonRequest) {
        TrainingBlock block = trainingBlockService.getBlockById(lessonRequest.getBlockId());
        Lesson lesson = new Lesson(lessonRequest, block);
        LessonResponse lessonResponse = lessonMapper.toResponse(lessonRepository.save(lesson));
        kafkaProducerService.sendMessage(new KafkaEvent(EventType.NEW_LESSON, lessonResponse.getId()));
        return lessonResponse;
    }

    @Override
    @Transactional
    public void setLessonStatus(UUID lessonId, StatusType status) {
        Lesson lesson = getLessonById(lessonId);
        if(status == StatusType.IN_PROGRESS && lesson.getStatus() == StatusType.NEW) {
            lesson.setStartAT(LocalDateTime.now());
            lesson.setStatus(status);
        } else if (status == StatusType.DONE && lesson.getStatus() == StatusType.IN_PROGRESS) {
            lesson.setEndAT(LocalDateTime.now());
            lesson.setStatus(status);
        } else {
            throw new ConflictException("Нельзя перевести задачу в указанный статус", "Конфликт");
        }

        lessonRepository.save(lesson);
        kafkaProducerService.sendMessage(new KafkaEvent(EventType.SET_STATUS, lessonId, status));
    }

    private Lesson getLessonById(UUID lessonId) {
        Lesson lesson = lessonRepository
                .findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Нет урока с таким id", "Нет данных"));

        UUID userId = lesson.getBlock().getEducation().getUserId();

        if(userId.equals(tokenService.getCurrentUserId())) {
            return lesson;
        } else {
            throw new ConflictException("Данные не принадлежат пользователю", "Конфликт");
        }
    }
}
