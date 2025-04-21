package bai_tap_them.case_study_furuma.repositories.facility;

import bai_tap_them.case_study_furuma.models.Facility;
import bai_tap_them.case_study_furuma.repositories.Repository;

import java.util.LinkedHashMap;

public interface IFacilityRepository extends Repository<Facility> {
    void add(Facility facility);

    LinkedHashMap<Facility, Integer> findFacilitiesNeedingMaintenance();
}