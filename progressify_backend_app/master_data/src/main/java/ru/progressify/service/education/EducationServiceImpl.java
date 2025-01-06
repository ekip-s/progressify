package ru.progressify.service.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.progressify.ConflictException;
import ru.progressify.NotFoundException;
import ru.progressify.model.education.Education;
import ru.progressify.model.education.EducationListResponse;
import ru.progressify.model.education.EducationRequest;
import ru.progressify.model.education.EducationResponse;
import ru.progressify.model.mapper.EducationMapper;
import ru.progressify.repository.EducationRepository;
import ru.progressify.service.TokenService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class EducationServiceImpl implements EducationService{

    private final TokenService tokenService;
    private final EducationMapper educationMapper;
    private final EducationRepository educationRepository;

    @Autowired
    public EducationServiceImpl(TokenService tokenService, EducationMapper educationMapper, EducationRepository educationRepository) {
        this.tokenService = tokenService;
        this.educationMapper = educationMapper;
        this.educationRepository = educationRepository;
    }

    @Override
    @Transactional
    public UUID createNewEdu(EducationRequest educationRequest) {
        Education education = educationMapper.toEdu(educationRequest);
        education.createNewEdu(tokenService.getCurrentUserId());
        return educationRepository.save(education).getId();
    }

    @Override
    public List<EducationListResponse> getEduByClientId() {
        UUID userId = tokenService.getCurrentUserId();
        return educationMapper.toResponseList(educationRepository.findAllByUserId(userId));
    }

    @Override
    public EducationResponse getEduByEduId(UUID eduId) {

        Education education = getEduByEduIdLocal(eduId);
        if(education.getUserId().equals(tokenService.getCurrentUserId())) {
            return educationMapper.toResponse(education);
        } else {
            throw new ConflictException("Задача принадлежит другому пользователю.", "Конфликт");
        }
    }

    @Override
    public Education getEduByEduIdLocal(UUID eduId) {
        return educationRepository.findById(eduId).orElseThrow(() -> new NotFoundException(
                "Нет обучения с указанным id", "Нет данных"));
    }
}