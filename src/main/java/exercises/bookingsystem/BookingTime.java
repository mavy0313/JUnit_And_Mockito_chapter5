package exercises.bookingsystem;

public class BookingTime {

    private String day;
    private String hour;

    public BookingTime(String day, String hour) {
        this.day = day;
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    @Override
    public boolean equals(Object anotherObject) {
        BookingTime anotherBookingTime = (BookingTime) anotherObject;
        return getDay().equalsIgnoreCase(anotherBookingTime.getDay())
                && getHour().equalsIgnoreCase(anotherBookingTime.getHour());
    }
}
