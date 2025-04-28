package bai_tap_them.case_study_furuma.repositories.facility;

import bai_tap_them.case_study_furuma.models.Facility;
import bai_tap_them.case_study_furuma.repositories.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface IFacilityRepository {
    void add(Facility facility);

    Facility findById(String id);

    List<Facility> findAllByType(String type);

    Map<Facility, Integer> findAll();

    Map<Facility, Integer> findFacilitiesNeedingMaintenance();
}