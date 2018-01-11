package exercises.bookingsystem;

import java.util.LinkedList;
import java.util.List;

public class Classroom {

    private String name;
    private int capacity;
    private List<Equipment> equipment = new LinkedList<>();
    private String day = "";
    private String hour = "";

    public Classroom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Classroom(String name, int capacity, Equipment equipment) {
        this(name, capacity);
        this.equipment.add(equipment);
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }
}
