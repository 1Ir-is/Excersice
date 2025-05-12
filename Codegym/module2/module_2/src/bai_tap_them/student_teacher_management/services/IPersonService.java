package bai_tap_them.student_teacher_management.services;

import java.util.List;

public interface IPersonService<T> {
    List<T> getAll();
    void add(T entity);
    void delete(String id);
    List<T> searchByName(String keyword);
    List<T> sortByName();
    void edit(String id, T updated);
}
