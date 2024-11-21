package utilidades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CalendarioDias {
    private LocalDate date;


    public CalendarioDias(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDisplayText() {
        return String.valueOf(date.getDayOfMonth());
    }
	}