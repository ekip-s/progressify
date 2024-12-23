package ru.progressify.service.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.progressify.ConflictException;
import ru.progressify.NotFoundException;
import ru.progressify.model.StatusType;
import ru.progressify.model.lesson.Lesson;
import ru.progressify.model.lesson.LessonRequest;
import ru.progressify.model.lesson.LessonResponse;
import ru.progressify.model.mapper.LessonMapper;
import ru.progressify.model.training_block.TrainingBlock;
import ru.progressify.repository.LessonRepository;
import ru.progressify.service.TokenService;
import ru.progressify.service.training_block.TrainingBlockService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final TrainingBlockService trainingBlockService;
    private final LessonMapper lessonMapper;
    private final TokenService tokenService;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, TrainingBlockService trainingBlockService, LessonMapper lessonMapper, TokenService tokenService) {
        this.lessonRepository = lessonRepository;
        this.trainingBlockService = trainingBlockService;
        this.lessonMapper = lessonMapper;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public LessonResponse addNewLesson(LessonRequest lessonRequest) {
        TrainingBlock block = trainingBlockService.getBlockById(lessonRequest.getBlockId());
        Lesson lesson = new Lesson(lessonRequest, block);
        return lessonMapper.toResponse(lessonRepository.save(lesson));
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
