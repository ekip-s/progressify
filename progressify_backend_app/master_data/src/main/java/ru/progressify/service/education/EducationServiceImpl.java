package ru.progressify.service.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exception.ConflictException;
import ru.exception.NotFoundException;
import ru.model.models.education.Education;
import ru.model.models.education.EducationListResponse;
import ru.model.models.education.EducationRequest;
import ru.model.models.education.EducationResponse;
import ru.progressify.mapper.EducationMapper;
import ru.progressify.producers.KafkaProducerService;
import ru.model.repository.EducationRepository;
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
    public EducationServiceImpl(TokenService tokenService, EducationMapper educationMapper, EducationRepository educationRepository, KafkaProducerService kafkaProducerService) {
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

    @Override
    @Transactional
    public void deleteEduByEduId(UUID eduId) {
        educationRepository.deleteById(eduId);
    }

    @Override
    @Transactional
    public void setAllEdu() {
        List<Education> educations = educationRepository.findAll();
        // Нужно заполнить:

        // Всего уроков
        // Выполненные задачи;
        // Дата старта EDU, если есть хоть одна задача с датой старта, нужно заполнить самую ранюю дата начала;
        // Дата завершения обучения, если всего уроков не 0 и всего уроков == завершенным урокам;

        // Дата начала блока == если есть хоть одна задача не в NEW; И нужна самая старая задача в рамках блока;
        // Дата завершения блока, если все задачи в блоке == DONE и задачи в принципе есть; И нужна самая новая запись в блоке;

    }
}