package exercises.bookingsystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookingSystem {
    private List<Classroom> existingClassrooms = new LinkedList<>();

    public void addClassroom(Classroom classroom) {
        existingClassrooms.add(classroom);
    }

    public List<Classroom> getExistingClassrooms() {
        return existingClassrooms;
    }

    //TODO: think how to store time, how to map classroom and time
    public List<Classroom> getAvailableClassrooms(String day, String hour) {
        List<Classroom> result = new ArrayList<>();
        for (Classroom classroom : existingClassrooms) {
            if (classroom.getDay().equalsIgnoreCase(day) || classroom.getHour().equalsIgnoreCase(hour)) {
                result.add(classroom);
            }
        }

        return result;
    }
}
