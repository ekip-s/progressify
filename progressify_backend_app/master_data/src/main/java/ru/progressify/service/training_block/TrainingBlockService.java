package ru.progressify.service.training_block;

import ru.model.models.training_block.TrainingBlock;
import ru.model.models.training_block.TrainingBlockRequest;
import ru.model.models.training_block.TrainingBlockResponse;

import java.util.UUID;

public interface TrainingBlockService {

    TrainingBlockResponse addNewBlock(TrainingBlockRequest trainingBlockRequest);
    TrainingBlock getBlockById(UUID blockId);
}
