package ru.progressify.service.training_block;

import ru.progressify.model.training_block.TrainingBlock;
import ru.progressify.model.training_block.TrainingBlockRequest;
import ru.progressify.model.training_block.TrainingBlockResponse;

import java.util.UUID;

public interface TrainingBlockService {

    TrainingBlockResponse addNewBlock(TrainingBlockRequest trainingBlockRequest);
    TrainingBlock getBlockById(UUID blockId);
}
