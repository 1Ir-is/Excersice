package bai_tap_them.case_study_furuma.repositories.promotion;

import bai_tap_them.case_study_furuma.models.Booking;
import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.utils.BookingComparator;
import bai_tap_them.case_study_furuma.utils.SaveFileUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class PromotionRepository implements IPromotionRepository {
    private static final String BOOKING_FILE = "bai_tap_them/case_study_furuma/data/bookings.csv";
    private static final String CUSTOMER_FILE = "bai_tap_them/case_study_furuma/data/customers.csv";
    private static final List<Customer> customers = new ArrayList<>();
    private static final TreeSet<Booking> bookings = new TreeSet<>(new BookingComparator());

    static {
        List<String> bookingLines = SaveFileUtils.readFromFile(BOOKING_FILE);
        for (String line : bookingLines) {
            bookings.add(Booking.fromCSV(line));
        }

        List<String> customerLines = SaveFileUtils.readFromFile(CUSTOMER_FILE);
        for (String line : customerLines) {
            customers.add(Customer.fromCSV(line));
        }
    }

    @Override
    public List<Customer> findCustomerByYear(int year) {
        List<Customer> result = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getBookingDate().contains(String.valueOf(year))) {
                for (Customer customer : customers) {
                    if (customer.getId().equals(booking.getCustomerId())) {
                        result.add(customer);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Stack<Customer> getCustomerForVoucher(int month, int totalVoucher) {
        Stack<Customer> customerStack = new Stack<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Booking booking : bookings) {
            try {
                LocalDate bookingDate = LocalDate.parse(booking.getBookingDate(), formatter);
                if (bookingDate.getMonthValue() == month) {
                    for (Customer customer : customers) {
                        if (customer.getId().equals(booking.getCustomerId())) {
                            customerStack.push(customer);
                            if (customerStack.size() == totalVoucher) {
                                return customerStack;
                            }
                        }
                    }
                }
            } catch (DateTimeParseException dateTimeParseException) {
                System.err.println("Invalid date format booking: " + booking.getBookingDate());
            }
        }
        return customerStack;
    }
}
