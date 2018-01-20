package exercises.bookingsystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookingSystem {
    private List<Classroom> existingClassrooms = new LinkedList<>();
    private List<Classroom> bookedClassrooms = new LinkedList<>();

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
            if (classroom.getDay().equalsIgnoreCase(day) && classroom.getHour().equalsIgnoreCase(hour)) {
                continue;
            }
            result.add(classroom);
        }

        return result;
    }

    public List<Classroom> getAvailableClassrooms() {
        List<Classroom> result = new ArrayList<>();
        for (Classroom classroom : existingClassrooms) {
            if (classroom.isBooked()) {
                continue;
            }
            result.add(classroom);
        }

        return result;
    }

    public void book(String classroomName, BookingTime bookingTime) {
        for (Classroom classroom : getAvailableClassrooms(bookingTime.getDay(), bookingTime.getHour())) {
            if (!classroomName.equalsIgnoreCase(classroom.getName())) {
                continue;
            }
            classroom.setBooked(true);

            classroom.setDay(bookingTime.getDay());
            classroom.setHour(bookingTime.getHour());

            getBookedClassrooms().add(classroom);
        }
    }

    public void book(int capacity, Equipment equipment, BookingTime bookingTime) {
        for (Classroom classroom : getAvailableClassrooms()) {
            if (classroom.getCapacity() != capacity || !classroom.getEquipment().contains(equipment)) {
                continue;
            }
            classroom.setBooked(true);

            classroom.setDay(bookingTime.getDay());
            classroom.setHour(bookingTime.getHour());

            getBookedClassrooms().add(classroom);
        }
    }

    public List<Classroom> getBookedClassrooms() {
        return bookedClassrooms;
    }

    public List<Classroom> getBookedClassrooms(BookingTime bookingTime) {
        List<Classroom> result = new ArrayList<>();
        for (Classroom classroom : getBookedClassrooms()) {
            if (classroom.getDay().equalsIgnoreCase(bookingTime.getDay())
                    && classroom.getHour().equalsIgnoreCase(bookingTime.getHour())) {
                result.add(classroom);
            }
        }
        return result;
    }
}
