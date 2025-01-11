package ru.progressify.mapper;

import org.mapstruct.Mapper;
import ru.progressify.lesson.Lesson;
import ru.progressify.lesson.LessonResponse;


@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponse toResponse(Lesson lesson);
}
