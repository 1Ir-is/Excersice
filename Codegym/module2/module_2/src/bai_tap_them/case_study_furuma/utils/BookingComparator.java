package bai_tap_them.case_study_furuma.utils;

import bai_tap_them.case_study_furuma.models.Booking;

import java.util.Comparator;

public class BookingComparator implements Comparator<Booking> {

    @Override
    public int compare(Booking o1, Booking o2) {
        int startComparison = o1.getStartDate().compareTo(o2.getStartDate());
        if (startComparison != 0) {
            return startComparison;
        }
        return o1.getEndDate().compareTo(o2.getEndDate());
    }
}
