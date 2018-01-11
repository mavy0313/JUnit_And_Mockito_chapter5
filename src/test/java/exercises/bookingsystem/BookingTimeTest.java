package exercises.bookingsystem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookingTimeTest {

    @Test
    public void should_Create_BookingTime() {
        BookingTime bookingTime = new BookingTime("Mon", "8:00");

        assertEquals("Mon", bookingTime.getDay());
        assertEquals("8:00", bookingTime.getHour());
    }

    @Test
    public void should_Compare_2_BookingTime_Objects() {
        BookingTime bookingTime1 = new BookingTime("Mon", "8:00");
        BookingTime bookingTime2 = new BookingTime("Mon", "8:00");
        BookingTime bookingTime3 = new BookingTime("Mon", "9:00");

        assertEquals(bookingTime1, bookingTime2);
        assertNotEquals(bookingTime1, bookingTime3);
    }
}
