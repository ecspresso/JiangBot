package com.ecspresso.mau.kronox;

import com.ecspresso.mau.kronox.locale.Room;
import com.ecspresso.mau.kronox.time.Time;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Agenda {
    private Room room;
    private boolean slot1 = false;
    private boolean slot2 = false;
    private boolean slot3 = false;
    private boolean slot4 = false;
    private boolean slot5 = false;

    public Agenda(Room room) {
        this.room = room;
    }

    public void addTime(Time time) {
        if(!slot1) {
            slot1 = time.collidesWith(getDate(8, 15), getDate(10, 15));
        }
        if(!slot2) {
            slot2 = time.collidesWith(getDate(10, 30), getDate(12, 30));
        }
        if(!slot3) {
            slot3 = time.collidesWith(getDate(13, 15), getDate(15, 15));
        }
        if(!slot4) {
            slot4 = time.collidesWith(getDate(15, 30), getDate(17, 30));
        }
        if(!slot5) {
            slot5 = time.collidesWith(getDate(17, 45), getDate(19, 45));
        }
    }

    @NotNull
    private static LocalDateTime getDate(int hour, int minute) {
        LocalDate date = LocalDate.now(); //.plusDays(2);
        return date.atTime(hour, minute);
    }

    public Room getRoom() {
        return room;
    }

    public boolean isSlot1() {
        return slot1;
    }

    public boolean isSlot2() {
        return slot2;
    }

    public boolean isSlot3() {
        return slot3;
    }

    public boolean isSlot4() {
        return slot4;
    }

    public boolean isSlot5() {
        return slot5;
    }

    public void print() {
        System.out.printf("%s: %s %s %s %s %s%n", room, slot1, slot2, slot3, slot4, slot5);
    }
}
