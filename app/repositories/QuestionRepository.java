package repositories;

import com.google.inject.ImplementedBy;
import entities.Question;
import services.QuestionService;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(QuestionService.class)
public interface QuestionRepository {
    List<Question> list();
    List<Question> getListQuestionById(Long id);
    List<Question> getListQuestionJPA(Long examId);
    Question getQuestionById(Long id);
    Question save(Question question);
    Question update(Question question);
}
