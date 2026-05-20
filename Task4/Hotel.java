package Task4;

import java.util.ArrayList;

public class Hotel {

    ArrayList<Room> rooms = new ArrayList<>();

    public Hotel() {

        rooms.add(new Room(101, "Standard", 2000, true));
        rooms.add(new Room(102, "Deluxe", 4000, true));
        rooms.add(new Room(103, "Suite", 7000, true));
    }
}