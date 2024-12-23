package ru.progressify.model.mapper;

import org.mapstruct.Mapper;
import ru.progressify.model.training_block.TrainingBlock;
import ru.progressify.model.training_block.TrainingBlockResponse;

@Mapper(componentModel = "spring")
public interface TrainingBlockMapper {

    TrainingBlockResponse toResponse(TrainingBlock trainingBlock);
}
