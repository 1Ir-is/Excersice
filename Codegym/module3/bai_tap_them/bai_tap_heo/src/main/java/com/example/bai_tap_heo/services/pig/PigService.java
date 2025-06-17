package com.example.bai_tap_heo.services.pig;

import com.example.bai_tap_heo.models.Origin;
import com.example.bai_tap_heo.models.Pig;
import com.example.bai_tap_heo.repositories.pig.IPigRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Origin> getAllOrigins() {
        return pigRepository.findAllOrigins();
    }

    @Override
    public List<Pig> searchPigs(Boolean sold, String pidNumber, Integer originId) {
        List<Pig> pigs = pigRepository.findAll();
        return pigs.stream()
                .filter(p -> (sold == null || p.isSold() == sold))
                .filter(p -> (pidNumber == null || pidNumber.isEmpty() || p.getPidNumber().contains(pidNumber)))
                .filter(p -> (originId == null || p.getOrigin().getId() == originId))
                .collect(Collectors.toList());
    }


}