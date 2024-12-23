package ru.progressify.service.training_block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.progressify.ConflictException;
import ru.progressify.NotFoundException;
import ru.progressify.model.education.Education;
import ru.progressify.model.mapper.TrainingBlockMapper;
import ru.progressify.model.training_block.TrainingBlock;
import ru.progressify.model.training_block.TrainingBlockRequest;
import ru.progressify.model.training_block.TrainingBlockResponse;
import ru.progressify.repository.TrainingBlockRepository;
import ru.progressify.service.TokenService;
import ru.progressify.service.education.EducationService;

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
