package ru.progressify.service.lesson;

import ru.progressify.StatusType;
import ru.progressify.lesson.LessonRequest;
import ru.progressify.lesson.LessonResponse;

import java.util.UUID;

public interface LessonService {

    LessonResponse addNewLesson(LessonRequest lessonRequest);
    void setLessonStatus(UUID lessonId, StatusType status);
}
