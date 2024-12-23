package ru.progressify.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.progressify.model.training_block.TrainingBlockRequest;
import ru.progressify.model.training_block.TrainingBlockResponse;
import ru.progressify.service.training_block.TrainingBlockService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/training_block/api/v1")
@Tag(name="training_block", description = "Методы для управления блоками курса")
public class TrainingBlockController {

    private final TrainingBlockService trainingBlockService;

    @Operation(
            summary = "Новый блок обучения",
            description = "Создает новый блок обучения"
    )
    @PostMapping
    public TrainingBlockResponse addNewBlock(@RequestBody TrainingBlockRequest trainingBlockRequest) {
        log.info("POST: TrainingBlockController addNewBlock, параметры: {}", trainingBlockRequest);
        return trainingBlockService.addNewBlock(trainingBlockRequest);
    }
}
