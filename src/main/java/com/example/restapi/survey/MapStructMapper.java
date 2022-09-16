package com.example.restapi.survey;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @Mapping(source = "question.id", target = "id")
    @Mapping(source = "question.description", target = "description")
    @Mapping(source = "question.options", target = "options")
    @Mapping(source = "question.correctAnswer", target = "correctAnswer")
    QuestionDto questionToQuestionDto(Question question);

    @Mapping(source = "survey.id", target = "id")
    @Mapping(source = "survey.title", target = "title")
    @Mapping(source = "survey.description", target = "description")
    @Mapping(source = "survey.questions", target = "questions")
    SurveyDto surveyToSurveyDto(Survey survey);
}
