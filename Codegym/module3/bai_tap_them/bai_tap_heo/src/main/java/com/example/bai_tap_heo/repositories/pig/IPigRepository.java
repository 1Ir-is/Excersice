package com.example.bai_tap_heo.repositories.pig;


import com.example.bai_tap_heo.models.Pig;

import java.util.List;

public interface IPigRepository {
    List<Pig> findAll();
    List<Pig> findByStatus(boolean sold);
    List<Pig> findByOrigin(int originId);
    Pig findById(int id);
    void save(Pig pig);
    void update(Pig pig);
    void delete(int id);
}