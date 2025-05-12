package bai_tap_them.student_teacher_management.repositories;

import java.util.List;

public interface IPersonRepository<T> {
    List<T> findAll();
    void save(List<T> data);
    void add(T entity);
    void delete(String id);
    List<T> searchByName(String keyword);
    List<T> sortByName();
    void edit(String id, T updated);
}