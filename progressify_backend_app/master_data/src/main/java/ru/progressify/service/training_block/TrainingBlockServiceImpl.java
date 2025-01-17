package ru.progressify.service.training_block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exception.ConflictException;
import ru.exception.NotFoundException;

import ru.model.models.education.Education;
import ru.progressify.mapper.TrainingBlockMapper;
import ru.model.repository.TrainingBlockRepository;
import ru.progressify.service.TokenService;
import ru.progressify.service.education.EducationService;
import ru.model.models.training_block.TrainingBlock;
import ru.model.models.training_block.TrainingBlockRequest;
import ru.model.models.training_block.TrainingBlockResponse;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class TrainingBlockServiceImpl implements TrainingBlockService {

    private final TrainingBlockRepository trainingBlockRepository;
    private final EducationService educationService;
    private final TrainingBlockMapper trainingBlockMapper;
    private final TokenService tokenService;

    @Autowired
    public TrainingBlockServiceImpl(TrainingBlockRepository trainingBlockRepository, EducationService educationService, TrainingBlockMapper trainingBlockMapper, TokenService tokenService) {
        this.trainingBlockRepository = trainingBlockRepository;
        this.educationService = educationService;
        this.trainingBlockMapper = trainingBlockMapper;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public TrainingBlockResponse addNewBlock(TrainingBlockRequest trainingBlockRequest) {
        Education education = educationService.getEduByEduIdLocal(trainingBlockRequest.getEduId());
        TrainingBlock block = new TrainingBlock(education, trainingBlockRequest);
        return trainingBlockMapper.toResponse(trainingBlockRepository.save(block));
    }

    @Override
    public TrainingBlock getBlockById(UUID blockId) {
        TrainingBlock block = trainingBlockRepository.findById(blockId)
                .orElseThrow(() -> new NotFoundException("Нет блока с таким id", "Нет данных"));
        if (block.getEducation().getUserId().equals(tokenService.getCurrentUserId())) {
            return block;
        } else {
            throw new ConflictException("Данные не принадлежат пользователю", "Конфликт");
        }
    }
}
