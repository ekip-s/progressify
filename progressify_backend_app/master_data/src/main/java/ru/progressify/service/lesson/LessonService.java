package ru.progressify.service.lesson;

import ru.progressify.model.StatusType;
import ru.progressify.model.lesson.LessonRequest;
import ru.progressify.model.lesson.LessonResponse;

import java.util.UUID;

public interface LessonService {

    LessonResponse addNewLesson(LessonRequest lessonRequest);
    void setLessonStatus(UUID lessonId, StatusType status);
}
