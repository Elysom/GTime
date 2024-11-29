package utilidades;
import java.time.DayOfWeek;
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
    public static String getDiaSemana(LocalDate date) {
        // Obtener el día de la semana como enum
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Convertir el enum a un nombre legible
        switch (dayOfWeek) {
            case MONDAY:
                return "L";
            case TUESDAY:
                return "M";
            case WEDNESDAY:
                return "X";
            case THURSDAY:
                return "J";
            case FRIDAY:
                return "V";
            case SATURDAY:
                return "S";
            case SUNDAY:
                return "D";
            default:
                throw new IllegalArgumentException("Día inválido");
        }
    }
    

    public String getDisplayText() {
        return String.valueOf(date.getDayOfMonth());
    }
	}