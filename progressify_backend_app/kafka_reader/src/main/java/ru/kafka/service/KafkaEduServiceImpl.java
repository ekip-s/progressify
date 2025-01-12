package ru.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exception.NotFoundException;
import ru.model.models.StatusType;
import ru.model.models.education.Education;
import ru.model.models.kafka.KafkaEvent;
import ru.model.models.lesson.Lesson;
import ru.model.models.training_block.TrainingBlock;
import ru.model.repository.EducationRepository;
import ru.model.repository.TrainingBlockRepository;

import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class KafkaEduServiceImpl implements KafkaEduService {

    private final EducationRepository educationRepository;
    private final TrainingBlockRepository trainingBlockRepository;

    @Autowired
    public KafkaEduServiceImpl(EducationRepository educationRepository, TrainingBlockRepository trainingBlockRepository) {
        this.educationRepository = educationRepository;
        this.trainingBlockRepository = trainingBlockRepository;
    }

    @Override
    @Transactional
    public void newLessonEventHandler(KafkaEvent event) {
        Education education = getEduByLessonId(event.getLessonId());
        education.newLesson();
        educationRepository.save(education);
    }

    @Override
    @Transactional
    public void setLessonStatusHandler(KafkaEvent event) {
        Education education = getEduByLessonId(event.getLessonId());
        TrainingBlock block = getTrainingBlockByLessonId(event.getLessonId());
        switch (event.getNewStatus()) {
            case IN_PROGRESS:
                if(block.getStartAT() == null) {
                    block.setStartAT(event.getEventAT());
                    block.setStatus(StatusType.IN_PROGRESS);
                    trainingBlockRepository.save(block);
                }
                if(education.getStartAT() == null) {
                    education.setStartAT(event.getEventAT());
                    education.setStatus(StatusType.IN_PROGRESS);
                    educationRepository.save(education);
                }
                break;
            case DONE:
                List<Lesson> lessons= block.getLessons()
                        .stream()
                        .filter(l -> l.getStatus() != StatusType.DONE)
                        .toList();
                if(lessons.isEmpty()) {
                    block.setEndAT(event.getEventAT());
                    block.setStatus(StatusType.DONE);
                    trainingBlockRepository.save(block);
                }
                if(education.getDoneEdu() + 1 == education.getTotal()) {
                    education.setEndAT(event.getEventAT());
                    education.setStatus(StatusType.DONE);
                }
                education.doneLesson();
                educationRepository.save(education);
                break;
        }
    }

    private Education getEduByLessonId(UUID lessonId) {
        return educationRepository
                .findByLesson(lessonId)
                .orElseThrow(() -> new NotFoundException("Нет обучения с таким уроком или обучение удалено",
                        "Нет данных"));
    }

    private TrainingBlock getTrainingBlockByLessonId(UUID lessonId) {
        return trainingBlockRepository
                .getBlockByLesson(lessonId)
                .orElseThrow(() -> new NotFoundException("Нет блока с таким уроком или обучение удалено",
                        "Нет данных"));
    }
}
