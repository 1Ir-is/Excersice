package bai_tap_them.student_teacher_management.repositories.student;

import bai_tap_them.case_study_furuma.repositories.Repository;
import bai_tap_them.student_teacher_management.models.Student;

import java.util.List;

public interface IStudentRepository extends Repository<Student> {
    void add(Student student);
    void saveAll(List<Student> students);
}
