package ru.progressify.mapper;

import org.mapstruct.Mapper;
import ru.model.models.education.Education;
import ru.model.models.education.EducationListResponse;
import ru.model.models.education.EducationRequest;
import ru.model.models.education.EducationResponse;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    Education toEdu(EducationRequest education);
    EducationResponse toResponse(Education education);
    List<EducationListResponse> toResponseList(List<Education> educations);
}
