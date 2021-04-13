package repositories;

import com.google.inject.ImplementedBy;
import entities.ExamResult;
import services.ExamResultService;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(ExamResultService.class)
public interface ExamResultRepository {
    CompletionStage<Stream<ExamResult>> list();

    CompletionStage<ExamResult> getExamResultById(Long id);

    CompletionStage<ExamResult> save(ExamResult examResult);

    CompletionStage<ExamResult> update(ExamResult examResult);
}
