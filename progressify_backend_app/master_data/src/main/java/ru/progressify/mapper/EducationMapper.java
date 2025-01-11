package ru.progressify.mapper;

import org.mapstruct.Mapper;
import ru.progressify.education.Education;
import ru.progressify.education.EducationListResponse;
import ru.progressify.education.EducationRequest;
import ru.progressify.education.EducationResponse;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    Education toEdu(EducationRequest education);
    EducationResponse toResponse(Education education);
    List<EducationListResponse> toResponseList(List<Education> educations);
}
