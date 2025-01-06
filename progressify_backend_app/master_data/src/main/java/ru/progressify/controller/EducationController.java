package ru.progressify.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.progressify.model.education.EducationListResponse;
import ru.progressify.model.education.EducationRequest;
import ru.progressify.model.education.EducationResponse;
import ru.progressify.service.education.EducationService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/education/api/v1")
@Tag(name="education_controller", description = "Методы для управления курсами")
public class EducationController {

    private final EducationService educationService;

    @Operation(
            summary = "Создание нового обучения",
            description = "Создает обучение, которое далее нужно наполнить курсами и уроками"
    )
    @PostMapping
    public UUID createNewEdu(@RequestBody EducationRequest educationRequest) {
        log.info("POST: EducationController createNewEdu, параметры: {}", educationRequest);
        return educationService.createNewEdu(educationRequest);
    }

    @Operation(
            summary = "Получение записей по клиенту",
            description = ""
    )
    @GetMapping
    public List<EducationListResponse> getEduByClientId() {
        log.info("GET: EducationController getEduByClientId");
        return educationService.getEduByClientId();
    }

    @Operation(
            summary = "Получение урока по id",
            description = ""
    )
    @GetMapping("/eduId/{eduId}")
    public EducationResponse getEduByEduId(@PathVariable UUID eduId) {
        log.info("GET: EducationController getEduByEduId, параметры: eduId={}", eduId);
        return educationService.getEduByEduId(eduId);
    }

    @Operation(
            summary = "Удаление урока по id",
            description = ""
    )
    @DeleteMapping("/eduId/{eduId}")
    public void deleteEduByEduId(@PathVariable UUID eduId) {
        log.info("DELETE: EducationController deleteEduByEduId, параметры: eduId={}", eduId);
        educationService.deleteEduByEduId(eduId);
    }
}
