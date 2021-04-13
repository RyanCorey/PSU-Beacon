package repositories;

import com.google.inject.ImplementedBy;
import entities.Exam;
import services.ExamService;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(ExamService.class)
public interface ExamRepository {

    CompletionStage<Stream<Exam>> list();

    CompletionStage<Exam> getExamById(Long id);

    Exam getExam(Long id);

    CompletionStage<Exam> save(Exam exam);

    Exam saveWait(Exam exam);

    CompletionStage<Exam> update(Exam exam);
}
