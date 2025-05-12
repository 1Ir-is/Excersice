package bai_tap_them.student_teacher_management.services;

import bai_tap_them.student_teacher_management.models.Teacher;
import bai_tap_them.student_teacher_management.repositories.TeacherRepository;

import java.util.List;

public class TeacherService implements IPersonService<Teacher> {
    private final TeacherRepository repository = new TeacherRepository();

    @Override
    public List<Teacher> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(Teacher entity) {
        repository.add(entity);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public List<Teacher> searchByName(String keyword) {
        return repository.searchByName(keyword);
    }

    @Override
    public List<Teacher> sortByName() {
        return repository.sortByName();
    }

    @Override
    public void edit(String id, Teacher updated) {
        repository.edit(id, updated);
    }
}
