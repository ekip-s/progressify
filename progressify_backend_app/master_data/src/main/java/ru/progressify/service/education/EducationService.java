package ru.progressify.service.education;

import ru.model.models.education.Education;
import ru.model.models.education.EducationListResponse;
import ru.model.models.education.EducationRequest;
import ru.model.models.education.EducationResponse;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    UUID createNewEdu(EducationRequest educationRequest);
    List<EducationListResponse> getEduByClientId();
    EducationResponse getEduByEduId(UUID eduId);
    Education getEduByEduIdLocal(UUID eduId);
    void deleteEduByEduId(UUID eduId);
    void setAllEdu();
}
