package repositories;

import com.google.inject.ImplementedBy;
import entities.Answer;
import entities.Question;
import services.AnswerService;

import java.util.List;

@ImplementedBy(AnswerService.class)
public interface AnswerRepository {
    Answer getAnswerById(Long examId);

    List<Answer> getAnswerListById(Long id);

    List<Answer> list();

    List<Answer> getListAnswerCountById(Long id);

    Answer save(Answer answer);

    Answer update(Answer answer);

    void delete(Long id);
}
