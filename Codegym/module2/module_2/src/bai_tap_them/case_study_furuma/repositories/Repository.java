package bai_tap_them.case_study_furuma.repositories;

import java.util.ArrayList;

public interface Repository<T> {

    ArrayList<T> findAll();
}
