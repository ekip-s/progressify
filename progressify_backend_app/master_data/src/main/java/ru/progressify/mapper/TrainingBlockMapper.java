package ru.progressify.mapper;

import org.mapstruct.Mapper;
import ru.progressify.training_block.TrainingBlock;
import ru.progressify.training_block.TrainingBlockResponse;

@Mapper(componentModel = "spring")
public interface TrainingBlockMapper {

    TrainingBlockResponse toResponse(TrainingBlock trainingBlock);
}
