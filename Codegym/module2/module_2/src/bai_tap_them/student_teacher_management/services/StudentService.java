package bai_tap_them.student_teacher_management.services;

import bai_tap_them.student_teacher_management.models.Student;
import bai_tap_them.student_teacher_management.repositories.StudentRepository;

import java.util.List;

public class StudentService implements IPersonService<Student> {
    private final StudentRepository repository = new StudentRepository();

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(Student entity) {
        repository.add(entity);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public List<Student> searchByName(String keyword) {
        return repository.searchByName(keyword);
    }

    @Override
    public List<Student> sortByName() {
        return repository.sortByName();
    }

    @Override
    public void edit(String id, Student updated) {
        repository.edit(id, updated);
    }
}