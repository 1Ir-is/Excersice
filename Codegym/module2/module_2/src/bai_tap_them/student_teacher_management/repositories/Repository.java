package bai_tap_them.student_teacher_management.repositories;

import java.util.ArrayList;

public interface Repository<T> {
    ArrayList<T> findAll();
}
