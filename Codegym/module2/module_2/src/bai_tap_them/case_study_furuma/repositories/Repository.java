package bai_tap_them.case_study_furuma.repositories;

import java.util.ArrayList;

public interface Repository<T> {
    void save(ArrayList<T> list);

    ArrayList<T> findAll();
}
