package exercises.bookingsystem;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BookingSystemTest {

    @Test
    public void should_Return_List_Of_All_Existing_Classrooms() {
        Classroom classroomA1 = new Classroom("A1", 10);
        Classroom classroomA2 = new Classroom("A2", 20, Equipment.PROJECTOR);
        Classroom classroomA3 = new Classroom("A3", 30, Equipment.PROJECTOR);

        BookingSystem bookingSystem = new BookingSystem();

        bookingSystem.addClassroom(classroomA1);
        bookingSystem.addClassroom(classroomA2);
        bookingSystem.addClassroom(classroomA3);

        assertTrue(bookingSystem.getExistingClassrooms().contains(classroomA1));
        assertTrue(bookingSystem.getExistingClassrooms().contains(classroomA2));
        assertTrue(bookingSystem.getExistingClassrooms().contains(classroomA3));
    }

    @Test
    public void should_Return_List_Of_All_Available_Classrooms() {
        Classroom classroomA1 = new Classroom("A1", 10);
        Classroom classroomA2 = new Classroom("A2", 20, Equipment.PROJECTOR);

        BookingSystem bookingSystem = new BookingSystem();

        bookingSystem.addClassroom(classroomA1);
        bookingSystem.addClassroom(classroomA2);

        assertTrue(bookingSystem.getAvailableClassrooms("Mon", "8:00").contains(classroomA1));
        assertTrue(bookingSystem.getAvailableClassrooms("Mon", "8:00").contains(classroomA2));
    }
}
