package bai_tap_them.case_study_furuma.models;

import java.time.LocalDate;
import java.util.Objects;

public class Booking implements Comparable<Booking> {
    private String bookingId;
    private String bookingDate;
    private String startDate;
    private String endDate;
    private String customerId;
    private String facilityId;

    public Booking() {
    }

    public Booking(String bookingId, String bookingDate, String startDate, String endDate, String customerId, String facilityId) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.facilityId = facilityId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    //Danh sách booking hiển thị phải được sắp xếp theo thứ tự ngày tháng booking gần nhất,
    // nếu trùng nhau thì sắp xếp theo ngày kết thúc thuê.
    public int compareTo(Booking other) {
        //Danh sách booking hiển thị phải được sắp xếp theo thứ tự ngày tháng booking gần nhất,
        int startComparison = this.startDate.compareTo(other.startDate);
        if (startComparison != 0) {
            return startComparison;
        }
        // nếu trùng nhau thì sắp xếp theo ngày kết thúc thuê.
        return this.endDate.compareTo(other.endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", facilityId='" + facilityId + '\'' +
                '}';
    }
}