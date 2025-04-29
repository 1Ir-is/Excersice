package bai_tap_them.case_study_furuma.repositories.contract;

import bai_tap_them.case_study_furuma.models.Contract;

import java.util.List;

public interface IContractRepository {
    void add(Contract contract);
    List<Contract> findAll();
}