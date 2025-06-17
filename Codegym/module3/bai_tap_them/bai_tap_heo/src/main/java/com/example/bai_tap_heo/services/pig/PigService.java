package com.example.bai_tap_heo.services.pig;

import com.example.bai_tap_heo.models.Pig;
import com.example.bai_tap_heo.repositories.pig.IPigRepository;

import java.util.List;

public class PigService implements IPigService {
    private IPigRepository pigRepository;

    public PigService(IPigRepository pigRepository) {
        this.pigRepository = pigRepository;
    }

    @Override
    public List<Pig> getAllPigs() {
        return pigRepository.findAll();
    }

    @Override
    public List<Pig> getPigsByStatus(boolean sold) {
        return pigRepository.findByStatus(sold);
    }

    @Override
    public List<Pig> getPigsByOrigin(int originId) {
        return pigRepository.findByOrigin(originId);
    }

    @Override
    public Pig getPigById(int id) {
        return pigRepository.findById(id);
    }

    @Override
    public void addPig(Pig pig) {
        pigRepository.save(pig);
    }

    @Override
    public void updatePig(Pig pig) {
        pigRepository.update(pig);
    }

    @Override
    public void deletePig(int id) {
        pigRepository.delete(id);
    }
}