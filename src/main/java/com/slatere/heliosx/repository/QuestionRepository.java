package com.slatere.heliosx.repository;

import com.slatere.heliosx.model.Question;
import com.slatere.heliosx.model.QuestionTypeEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuestionRepository {

    List<Question> questionList;

    @PostConstruct
    private void setUp() {
        questionList = new ArrayList<>();
        questionList.add(createQuestion(UUID.fromString("cf136958-4670-416f-a330-fbfaa7f1f220"),
                "What is your first name?", null, null, QuestionTypeEnum.STRING));
        questionList.add(createQuestion(UUID.fromString("3e438d3e-2301-47f7-8066-b8ac20ab44f0"),
                "What is your last name?", null, null, QuestionTypeEnum.STRING));
        String[] ageAnswers = {"18 - 25","26 - 40","41 - 60","61+"};
        String[] ageValidAnswers = {"18 - 25","26 - 40"};
        questionList.add(createQuestion(UUID.fromString("53fa49f8-aa37-4565-94fd-ca2b27f8e973"),
                "What is your age range?", Arrays.asList(ageAnswers), Arrays.asList(ageValidAnswers),
                QuestionTypeEnum.MULTICHOICE));
        String[] yesNoAnswers = {"Yes","No"};
        String[] yesNoValidAnswers = {"No"};
        questionList.add(createQuestion(UUID.fromString("bbae8577-78d6-4692-98b0-5fb07e2e4b2e"),
                "ave you had an allergic reaction to this medication?", Arrays.asList(yesNoAnswers),
                Arrays.asList(yesNoValidAnswers), QuestionTypeEnum.MULTICHOICE));
    }

    private Question createQuestion(UUID questionId, String text, List<String>  answers, List<String> validAnswers,
                                    QuestionTypeEnum questionTypeEnum) {
        UUID consultationId = UUID.fromString("c2c9881e-f6ce-46b4-8977-8108399e0866");
        return new Question(
                questionId,
                consultationId,
                text,
                answers,
                validAnswers,
                questionTypeEnum
        );
    }

    public Question save(Question question) {
        questionList.add(question);
        return question;
    }

    public void delete(Question question) {
        questionList.remove(question);
    }

    public List<Question> getAll() {
        return questionList;
    }

    public Optional<Question> findById(UUID id) {
        for (Question question: questionList) {
            if (question.getId().equals(id)) {
                return Optional.of(question);
            }
        }
        return Optional.empty();
    }

    public List<Question> findByConsultationId(UUID consultationId) {
        List<Question> questionByConsultationId = new ArrayList<>();
        for (Question question: questionList) {
            if (question.getConsultationId().equals(consultationId)) {
                questionByConsultationId.add(question);
            }
        }
        return questionByConsultationId;
    }
}
