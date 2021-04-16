package repositories;

import com.google.inject.ImplementedBy;
import entities.Exam;
import services.ExamService;

import java.util.List;

@ImplementedBy(ExamService.class)
public interface ExamRepository {

    List<Exam> list();

    Exam getExamById(Long id);

    Exam getExam(Long id);

    Exam save(Exam exam);

    Exam update(Exam exam);
}
