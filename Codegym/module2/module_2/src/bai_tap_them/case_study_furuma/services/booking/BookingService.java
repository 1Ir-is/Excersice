package bai_tap_them.case_study_furuma.services.booking;

import bai_tap_them.case_study_furuma.models.Booking;
import bai_tap_them.case_study_furuma.repositories.booking.IBookingRepository;

import java.util.Scanner;
import java.util.TreeSet;

public class BookingService implements IBookingService {
    private final IBookingRepository bookingRepository;
    private final Scanner scanner = new Scanner(System.in);

    public BookingService(IBookingRepository repository) {
        this.bookingRepository = repository;
    }

    @Override
    public void addBooking() {
        
    }

    @Override
    public void displayBooking() {
        TreeSet<Booking> bookings = bookingRepository.findAll();
        if (bookings.isEmpty()) {
            System.out.println("Nothing");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}
