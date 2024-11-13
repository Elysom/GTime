package utilidades;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarioMetodos {

	    private YearMonth actualYearMonth;
	    private Map<LocalDate, CalendarioDias> diasCalendario;

	    public CalendarioMetodos() {
	        this.actualYearMonth = YearMonth.now();
	        this.diasCalendario = new HashMap<>();
	        loadDaysInMonth();
	    }
	    // Método para obtener el desplazamiento del primer día del mes
	    public int getDayOfWeekOffset() {
	        YearMonth currentMonth = getCurrentYearMonth();
	        LocalDate firstDayOfMonth = LocalDate.of(currentMonth.getYear(), currentMonth.getMonth(), 1);
	        
	        // Obtener el día de la semana del primer día del mes (lunes = 1, ..., domingo = 7)
	        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
	        dayOfWeek--;
	        // Ajustar el valor para que el domingo sea 0, lunes 1, ..., sábado 6
	        // Si es domingo (7 en la API de Java), lo cambiamos a 0.
	        return (dayOfWeek == 7) ? 0 : dayOfWeek;
	    }
	    public YearMonth getCurrentYearMonth() {
	        return actualYearMonth;
	    }

	    public void previousMonth() {
	        actualYearMonth = actualYearMonth.minusMonths(1);
	        loadDaysInMonth();
	    }

	    public void nextMonth() {
	        actualYearMonth = actualYearMonth.plusMonths(1);
	        loadDaysInMonth();
	    }

	    private void loadDaysInMonth() {
	        diasCalendario.clear();
	        LocalDate firstOfMonth = actualYearMonth.atDay(1);
	        int daysInMonth = actualYearMonth.lengthOfMonth();

	        for (int i = 1; i <= daysInMonth; i++) {
	            LocalDate date = firstOfMonth.withDayOfMonth(i);
	            diasCalendario.put(date, new CalendarioDias(date));
	        }
	    }

	    public List<CalendarioDias> getDaysInMonth() {
	        List<CalendarioDias> days = new ArrayList<>();
	        LocalDate startOfMonth = actualYearMonth.atDay(1);
	        int dayOfWeekOffset = startOfMonth.getDayOfWeek().getValue() % 7;

	        // Días vacíos para alineación en el calendario
	        for (int i = 0; i < dayOfWeekOffset; i++) {
	            days.add(null); // Añadir espacio vacío para días antes del inicio del mes
	        }

	        // Días del mes actual
	        for (int i = 1; i <= actualYearMonth.lengthOfMonth(); i++) {
	            LocalDate date = startOfMonth.withDayOfMonth(i);
	            days.add(diasCalendario.get(date)); // Añadir el día correspondiente al calendario
	        }

	        return days;
	    }

	    

	    public void addTask(LocalDate date, String task) {
	        diasCalendario.computeIfAbsent(date, CalendarioDias::new).addTask(task);
	    }
	
  
}
