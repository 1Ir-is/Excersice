package bai_tap_them.case_study_furuma.repositories.facility;

import bai_tap_them.case_study_furuma.models.Facility;
import bai_tap_them.case_study_furuma.repositories.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IFacilityRepository {
    void add(Facility facility);
    Map<Facility, Integer> findAll();
    Map<Facility, Integer> findFacilitiesNeedingMaintenance();
}