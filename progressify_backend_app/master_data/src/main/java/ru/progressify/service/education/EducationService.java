package ru.progressify.service.education;

import ru.progressify.education.Education;
import ru.progressify.education.EducationListResponse;
import ru.progressify.education.EducationRequest;
import ru.progressify.education.EducationResponse;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    UUID createNewEdu(EducationRequest educationRequest);
    List<EducationListResponse> getEduByClientId();
    EducationResponse getEduByEduId(UUID eduId);
    Education getEduByEduIdLocal(UUID eduId);
    void deleteEduByEduId(UUID eduId);
}
