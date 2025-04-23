package bai_tap_them.case_study_furuma.repositories.booking;

import bai_tap_them.case_study_furuma.models.Booking;
import bai_tap_them.case_study_furuma.utils.BookingComparator;

import java.util.TreeSet;

public class BookingRepository implements IBookingRepository {
    private static final TreeSet<Booking> bookings = new TreeSet<>(new BookingComparator());

    static {
        bookings.add(new Booking("B001", "2023-10-01", "2023-10-01", "2023-10-05", "C001", "SVVL-0001"));
        bookings.add(new Booking("B002", "2023-10-03", "2023-10-03", "2023-10-07", "C002", "SVHO-0001"));
    }

    @Override
    public TreeSet<Booking> findAll() {
        return bookings;
    }

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
    }
}