package repositories;

import com.google.inject.ImplementedBy;
import entities.ExamResult;
import services.ExamResultService;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(ExamResultService.class)
public interface ExamResultRepository {
    List<ExamResult> list();

    ExamResult getExamResultById(Long id);

    ExamResult save(ExamResult examResult);

    ExamResult update(ExamResult examResult);
}
