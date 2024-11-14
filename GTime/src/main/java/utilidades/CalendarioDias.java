package utilidades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CalendarioDias {
    private LocalDate date;
    private List<String> tasks;

    public CalendarioDias(LocalDate date) {
        this.date = date;
        this.tasks = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public List<String> getTasks() {
        return tasks;
    }

    public String getDisplayText() {
        return String.valueOf(date.getDayOfMonth());
    }
	}