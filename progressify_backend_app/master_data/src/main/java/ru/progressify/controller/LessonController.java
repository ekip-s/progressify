package ru.progressify.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.progressify.StatusType;
import ru.progressify.lesson.LessonRequest;
import ru.progressify.lesson.LessonResponse;
import ru.progressify.service.lesson.LessonService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson/api/v1")
@Tag(name="lesson_controller", description = "Методы для управления уроками")
public class LessonController {

    private final LessonService lessonService;

    @Operation(
            summary = "Создание нового урока",
            description = "Создет задачу под блоком"
    )
    @PostMapping
    public LessonResponse addNewLesson(@RequestBody LessonRequest lessonRequest) {
        log.info("POST: LessonController addNewLesson, параметры: {}", lessonRequest);
        return lessonService.addNewLesson(lessonRequest);
    }

    @Operation(
            summary = "Обновление статуса урока",
            description = "Можно изменить статус с NEW на IN_PROGRESS и с IN_PROGRESS в DONE. " +
                    "При других комбинациях будет ошибка"
    )
    @PatchMapping("lessonId/{lessonId}/status/{status}")
    public void setLessonStatus(@PathVariable UUID lessonId, @PathVariable StatusType status) {
        log.info("PATCH: LessonController setLessonStatus, параметры: lessonId={}, status={}"
                , lessonId, status);
        lessonService.setLessonStatus(lessonId, status);
    }
}
