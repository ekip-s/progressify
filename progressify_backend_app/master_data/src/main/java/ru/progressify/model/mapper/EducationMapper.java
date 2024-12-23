package ru.progressify.model.mapper;

import org.mapstruct.Mapper;
import ru.progressify.model.education.Education;
import ru.progressify.model.education.EducationListResponse;
import ru.progressify.model.education.EducationRequest;
import ru.progressify.model.education.EducationResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    Education toEdu(EducationRequest education);
    EducationResponse toResponse(Education education);
    List<EducationListResponse> toResponseList(List<Education> educations);
}
