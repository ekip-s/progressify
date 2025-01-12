package ru.progressify.mapper;

import org.mapstruct.Mapper;
import ru.model.models.lesson.Lesson;
import ru.model.models.lesson.LessonResponse;


@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponse toResponse(Lesson lesson);
}
