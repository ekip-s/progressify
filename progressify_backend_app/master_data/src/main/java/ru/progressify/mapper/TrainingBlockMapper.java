package ru.progressify.mapper;

import org.mapstruct.Mapper;
import ru.model.models.training_block.TrainingBlock;
import ru.model.models.training_block.TrainingBlockResponse;

@Mapper(componentModel = "spring")
public interface TrainingBlockMapper {

    TrainingBlockResponse toResponse(TrainingBlock trainingBlock);
}
