package bai_tap_them.case_study_furuma.repositories.booking;

import bai_tap_them.case_study_furuma.models.Booking;
import bai_tap_them.case_study_furuma.repositories.Repository;

import java.util.TreeSet;

public interface IBookingRepository {
    TreeSet<Booking> findAll();
    boolean update(Booking updatedBooking);
    void add(Booking booking);
}

