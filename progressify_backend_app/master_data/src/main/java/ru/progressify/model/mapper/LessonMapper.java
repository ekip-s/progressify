package ru.progressify.model.mapper;

import org.mapstruct.Mapper;
import ru.progressify.model.lesson.Lesson;
import ru.progressify.model.lesson.LessonResponse;


@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponse toResponse(Lesson lesson);
}
