package repositories;

import com.google.inject.ImplementedBy;
import entities.Answer;
import services.AnswerService;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(AnswerService.class)
public interface AnswerRepository {

    CompletionStage<Stream<Answer>> list();

    CompletionStage<Answer> getAnswerById(Long id);

    CompletionStage<Answer> save(Answer answer);

    Answer saveWait(Answer answer);

    CompletionStage<Answer> update(Answer answer);
}
