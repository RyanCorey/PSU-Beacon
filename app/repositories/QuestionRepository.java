package repositories;

import com.google.inject.ImplementedBy;
import entities.Question;
import services.QuestionService;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(QuestionService.class)
public interface QuestionRepository {
    CompletionStage<Stream<Question>> list();

    CompletionStage<Question> getQuestionById(Long id);

    CompletionStage<Question> save(Question question);

    Question saveWait(Question question);

    CompletionStage<Question> update(Question question);
}
