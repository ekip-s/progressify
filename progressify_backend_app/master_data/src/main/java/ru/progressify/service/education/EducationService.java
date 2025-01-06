package ru.progressify.service.education;

import ru.progressify.model.education.Education;
import ru.progressify.model.education.EducationListResponse;
import ru.progressify.model.education.EducationRequest;
import ru.progressify.model.education.EducationResponse;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    UUID createNewEdu(EducationRequest educationRequest);
    List<EducationListResponse> getEduByClientId();
    EducationResponse getEduByEduId(UUID eduId);
    Education getEduByEduIdLocal(UUID eduId);
}
