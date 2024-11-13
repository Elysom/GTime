package utilidades;

import java.time.LocalDateTime;

public class CalendarioTareas {

    private LocalDateTime dateTime;
    private String description;

    public CalendarioTareas(int day, int month, int year, int hour, int minute, String description) {
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return dateTime.toLocalDate() + " " + dateTime.toLocalTime() + " - " + description;
    }
}
