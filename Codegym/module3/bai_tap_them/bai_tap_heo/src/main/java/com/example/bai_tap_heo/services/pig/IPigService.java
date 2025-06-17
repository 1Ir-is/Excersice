package com.example.bai_tap_heo.services.pig;


import com.example.bai_tap_heo.models.Origin;
import com.example.bai_tap_heo.models.Pig;

import java.util.List;

public interface IPigService {
    List<Pig> getAllPigs();
    List<Pig> getPigsByStatus(boolean sold);
    List<Pig> getPigsByOrigin(int originId);
    Pig getPigById(int id);
    void addPig(Pig pig);
    void updatePig(Pig pig);
    void deletePig(int id);
    List<Origin> getAllOrigins();
    List<Pig> searchPigs(Boolean sold, String pidNumber, Integer originId);
}