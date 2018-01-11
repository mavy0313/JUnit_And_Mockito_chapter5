package exercises.bookingsystem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassroomTest {

    @Test
    public void shouldCreateClassroomWithEquipment() {
        Classroom classroom = new Classroom("A1", 20, Equipment.PROJECTOR);

        assertEquals("A1", classroom.getName());
        assertEquals(20, classroom.getCapacity());
        assertTrue(classroom.getEquipment().contains(Equipment.PROJECTOR));
    }
}
