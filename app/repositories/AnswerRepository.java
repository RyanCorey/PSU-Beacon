package repositories;

import com.google.inject.ImplementedBy;
import entities.Answer;
import services.AnswerService;

import java.util.List;

@ImplementedBy(AnswerService.class)
public interface AnswerRepository {
    List<Answer> list();

    Answer getAnswerById(Long id);

    Answer save(Answer answer);

    Answer update(Answer answer);
}
