package ru.progressify.service.lesson;

import ru.model.models.StatusType;
import ru.model.models.lesson.LessonRequest;
import ru.model.models.lesson.LessonResponse;

import java.util.UUID;

public interface LessonService {

    LessonResponse addNewLesson(LessonRequest lessonRequest);
    void setLessonStatus(UUID lessonId, StatusType status);
}
