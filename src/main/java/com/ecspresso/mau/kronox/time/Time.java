package com.ecspresso.mau.kronox.time;

import java.time.LocalDateTime;

public record Time(LocalDateTime start, LocalDateTime end) {
    public boolean collidesWith(LocalDateTime start, LocalDateTime end) {
        return (this.start.isAfter(start) && this.start.isBefore(end)) || (this.end.isAfter(start) && this.end.isBefore(end));
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }


}
