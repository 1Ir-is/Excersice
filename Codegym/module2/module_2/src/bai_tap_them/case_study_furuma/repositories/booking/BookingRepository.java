package bai_tap_them.case_study_furuma.repositories.booking;

import bai_tap_them.case_study_furuma.models.Booking;
import bai_tap_them.case_study_furuma.utils.BookingComparator;
import bai_tap_them.case_study_furuma.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;

public class BookingRepository implements IBookingRepository {
    private static final TreeSet<Booking> bookings = new TreeSet<>(new BookingComparator());
    private static final String BOOKING_FILE = "bai_tap_them/case_study_furuma/data/bookings.csv";

    public BookingRepository() {
        loadBookingsFromFile();
    }

    @Override
    public TreeSet<Booking> findAll() {
        return bookings;
    }

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
        saveBookingToFile(booking);
    }

    private void saveBookingToFile(Booking booking) {
        List<String> dataLines = new ArrayList<>();
        dataLines.add(booking.toCSV());
        SaveFileUtils.writeToFile(BOOKING_FILE, dataLines, true);
    }

    private void loadBookingsFromFile() {
        List<String> lines = SaveFileUtils.readFromFile(BOOKING_FILE);
        for (String line : lines) {
            bookings.add(Booking.fromCSV(line));
        }
    }
}