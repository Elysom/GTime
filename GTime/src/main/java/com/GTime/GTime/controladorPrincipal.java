package com.GTime.GTime;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import utilidades.CalendarioDias;
import utilidades.CalendarioMetodos;
import utilidades.CalendarioTareas;

import java.time.LocalDate;
import java.time.YearMonth;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class controladorPrincipal implements Initializable{
	static String nombreUsuario;
	@FXML
	public GridPane calendarGrid;
	@FXML
	private Label lblNUsuario;
    @FXML
    private Text month;
    @FXML
    private Text year;
    @FXML
    private Button atrasMes;
    @FXML
    private Button alanteMes;
    @FXML
    private TextField searchBox;
    @FXML
    private ListView<String> taskList;
    
    CalendarioDias calendarioDias;

    private CalendarioMetodos calendarioMetodos;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNUsuario.setText(nombreUsuario);
        calendarioMetodos = new CalendarioMetodos();

        updateCalendar();
        
	}

    @FXML
    private void previousMonth() {
        calendarioMetodos.previousMonth();
        System.out.println("Se ejecuta anterior");
        updateCalendar();
    }

    @FXML
    private void nextMonth() {
        calendarioMetodos.nextMonth();
        System.out.println("Se ejecuta siguiente");
        updateCalendar();
    }

    @FXML
    private void updateCalendar() {
        // Actualiza el mes y año en la interfaz
        YearMonth currentMonth = calendarioMetodos.getCurrentYearMonth();
        month.setText(currentMonth.getMonth().name());
        year.setText(String.valueOf(currentMonth.getYear()));

        // Limpiar el GridPane antes de actualizarlo
        calendarGrid.getChildren().clear();

        // Limpiar las restricciones de las filas y columnas
        calendarGrid.getRowConstraints().clear();
        calendarGrid.getColumnConstraints().clear();

        // Configurar las restricciones de las columnas y filas del GridPane
        configureGridPaneSize();

        // Obtener los días en formato CalendarDay para el mes actual
        List<CalendarioDias> days = calendarioMetodos.getDaysInMonth();

        // Mostrar los días en el GridPane
        int row = 0, col = 0;

        // Días vacíos para la alineación del primer día
        for (int i = 0; i < calendarioMetodos.getDayOfWeekOffset(); i++) {
            // Crear un espacio vacío para días antes del primer día del mes
            calendarGrid.add(new Label(""), col, row);
            col++;
        }

        // Crear un GridPane para cada día y añadirlo al calendario
        for (CalendarioDias day : days) {
            if (day != null) {
                // Crear un GridPane para el día
                GridPane dayGrid = new GridPane();
                dayGrid.setVgap(5);
                dayGrid.setHgap(5);
                dayGrid.setStyle("-fx-border-color: lightgray; -fx-padding: 5;");

                // Añadir el número del día como un Label
                Label dayLabel = new Label(day.getDisplayText());
                dayLabel.setStyle("-fx-font-weight: bold;");
                dayGrid.add(dayLabel, 0, 0);

                // Añadir las tareas del día
                List<String> tasks = day.getTasks();
                int rowTask = 1;
                for (String task : tasks) {
                    Label taskLabel = new Label(task);
                    dayGrid.add(taskLabel, 0, rowTask++);
                }

                // Añadir el GridPane del día al calendario
                calendarGrid.add(dayGrid, col, row);

                col++;
                if (col == 7) {  // Si hemos llenado una fila, pasamos a la siguiente
                    col = 0;
                    row++;
                }
            }
        }
    }

    private void configureGridPaneSize() {
        // Configura las restricciones para las columnas y filas del GridPane
        // Establecer las restricciones de las columnas (7 columnas para los días de la semana)
        for (int i = 0; i < 7; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 7);  // Cada columna ocupa el 14.29% del espacio disponible
            calendarGrid.getColumnConstraints().add(column);
        }

        // Configura las restricciones para las filas (6 filas para los días)
        // Hay un máximo de 6 filas por mes
        for (int i = 0; i < 6; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 6);  // Cada fila ocupa el 16.67% del espacio disponible
            calendarGrid.getRowConstraints().add(row);
        }
    }


  /*
    private void showTasksForDay(CalendarioDias day) {
        taskList.getItems().clear();
        taskList.getItems().addAll(day.getTasks());
    }
*/


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	
	
}
