package com.ecspresso.mau.kronox;

import com.ecspresso.mau.kronox.locale.Building;
import com.ecspresso.mau.kronox.locale.Room;
import com.ecspresso.mau.kronox.time.Time;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Schedule {

    private final HashMap<String, Agenda> events = new HashMap<>();

    public Schedule() {
        for(Room room : Room.values()) {
            events.put(room.toString(), new Agenda(room));
        }
    }

    public void addEvent(String location, LocalDateTime start, LocalDateTime end) {
        try {
            events.get(location).addTime(new Time(start, end));
        } catch(NullPointerException ignored) {}
    }

    public List<Agenda> getAgenda(Building building) {
        return events.values().stream().filter(agenda -> agenda.getRoom().getBuilding().equals(building)).toList();
    }
}
