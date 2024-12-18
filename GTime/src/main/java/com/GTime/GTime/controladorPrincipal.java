package com.GTime.GTime;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.PlanAcademico;
import modelo.Rutina;
import modelo.Tarea;
import utilidades.CalendarioDias;
import utilidades.CalendarioMetodos;
import utilidades.CalendarioTareas;
import utilidades.SCRUDusuarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class controladorPrincipal implements Initializable {
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

	@FXML
	private Button btnCrearCurso;

	@FXML
	private Button eliminarPlan;

	@FXML
	private TextField txtBuscar;

	public static String tipoDeUsuario = "";

	CalendarioDias calendarioDias;

	private CalendarioMetodos calendarioMetodos;

	public List<String> colores = new ArrayList<>();

	public List<String> listaTareas;

	public static String infoSeleccionada;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNUsuario.setText(controladorLoggin.nombreUsuGlobal);
		calendarioMetodos = new CalendarioMetodos();

        updateCalendar();
        
        listaTareas = agregarPlanesLista();
        
        taskList.setItems(FXCollections.observableArrayList(listaTareas));
        
        
        // Foreach List view
        taskList.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
            	System.out.println("entro el cell factory");
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle(""); // Estilo por defecto
                } else {
                    setText(item);

                    // Obtener el color de fondo
                    String color = obtenerColorParaItem(item, SCRUDusuarios.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal));

                    // Validar si el color es válido
                    if (color == null || color.isEmpty()) {
                        color = "white";  // Color por defecto
                    }

                    // Verificar que el color sea válido antes de aplicarlo
                    System.out.println("Color para item: " + color);

                    // Aplicar el estilo con el color
                    setStyle("-fx-background-color: " + color + ";");
                }
            }
        });
        
        
       
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            
        buscarElemento(); // Llama a buscarElemento con el nuevo texto
        
        	
        });
        
        // mostrar mas informacion
        
        taskList.setOnMouseClicked(event -> {
        	
        	if (event.getClickCount() == 2) { 
        		 infoSeleccionada = taskList.getSelectionModel().getSelectedItem();
        		  
            	 taskList.getSelectionModel().clearSelection();
                	
                   	
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/informacionDetallada.fxml"));
                	
                try {
                	Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}
        
        });
        
        
        
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

	// Funcion para actualizar el calendario
	@FXML
	private void updateCalendar() {
		// Actualiza el mes y año en la interfaz
		YearMonth currentMonth = calendarioMetodos.getCurrentYearMonth();
		switch (currentMonth.getMonth()) {
		case JANUARY:
			month.setText("Enero");
			break;
		case FEBRUARY:
			month.setText("Febrero");
			break;
		case MARCH:
			month.setText("Marzo");
			break;
		case APRIL:
			month.setText("Abril");
			break;
		case MAY:
			month.setText("Mayo");
			break;
		case JUNE:
			month.setText("Junio");
			break;
		case JULY:
			month.setText("Julio");
			break;
		case AUGUST:
			month.setText("Agosto");
			break;
		case SEPTEMBER:
			month.setText("Septiembre");
			break;
		case OCTOBER:
			month.setText("Octubre");
			break;
		case NOVEMBER:
			month.setText("Noviembre");
			break;
		case DECEMBER:
			month.setText("Diciembre");
			break;
		}// SWITCH PARA CAMBIAR EL NOMBRE DEL MES AL ESPAÑOL
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
				int colPlan = 1;
				int contador = 1;
				int items = 0;

				if (items < 4) {
					List<PlanAcademico> listaplanes = SCRUDusuarios.rellanarListaAdminEspecifico(
							SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal));
					for (PlanAcademico pa : listaplanes) {
						// Si el mes y el año y dia es el mismo al que esta dibujando se añade una label
						// con el plan
						if (pa.getFechahorasPlan().getDayOfMonth() == Integer.parseInt(day.getDisplayText())
								&& pa.getFechahorasPlan().getMonthValue() == currentMonth.getMonthValue()
								&& pa.getFechahorasPlan().getYear() == currentMonth.getYear()) {
							Label nplanLabel = new Label(contador + "." + pa.getNombrePlan());
							nplanLabel.setOnMouseClicked(event -> {
								infoSeleccionada = pa.toString();
								lanzarVentanaInformacionDetallada();
							});
							dayGrid.add(nplanLabel, 0, colPlan++);
							contador++;
						}
					}

				}
				// Verificar si el día es hoy
				// Obtener la fecha actual
				LocalDate today = LocalDate.now();
				LocalDate dayDate = day.getDate();
				if (dayDate.equals(today)) {
					dayGrid.setStyle("-fx-border-color: green; -fx-padding: 5;"); // Cambiar el estilo a verde
				}
				if (items >= 4) {
					Label nplanLabel = new Label("...");
					dayGrid.add(nplanLabel, 0, colPlan++);
				}

				// Añadir el GridPane del día al calendario
				calendarGrid.add(dayGrid, col, row);

				col++;
				if (col == 7) { // Si hemos llenado una fila, pasamos a la siguiente
					col = 0;
					row++;
				}
			}
		}
	}

	private void configureGridPaneSize() {
		// Configura las restricciones para las columnas y filas del GridPane
		// Establecer las restricciones de las columnas (7 columnas para los días de la
		// semana)
		for (int i = 0; i < 7; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(100.0 / 7); // Cada columna ocupa el 15% aprox del espacio disponible
			calendarGrid.getColumnConstraints().add(column);
		}

		// Configura las restricciones para las filas (6 filas para los días)
		// Hay un máximo de 6 filas por mes
		for (int i = 0; i < 6; i++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(100.0 / 6); // Cada fila ocupa el 16.67% del espacio disponible
			calendarGrid.getRowConstraints().add(row);
		}
	}

	@FXML
	private List<String> agregarPlanesLista() {

		List<PlanAcademico> listadePlanesAcademico = SCRUDusuarios
				.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal);

		List<String> listaTareas = new ArrayList<>();

		for (PlanAcademico planAcademico : listadePlanesAcademico) {

			listaTareas.add(planAcademico.toString());

			colores.add(planAcademico.getColor());
		}

		return listaTareas;

	}

	@FXML
	private void buscarElemento() {

		System.out.println("Método buscarElemento invocado");

		String palabra_busqueda = txtBuscar.getText();

		List<PlanAcademico> ejecutarBusqueda = SCRUDusuarios
				.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal);

		List<String> resultadoBusqueda = new ArrayList();

		for (PlanAcademico aux : ejecutarBusqueda) {

			if (aux.getNombrePlan().toLowerCase().contains(palabra_busqueda)
					|| aux.getNombrePlan().contains(palabra_busqueda)) {

				resultadoBusqueda.add(aux.toString());

			}
		}

		taskList.setItems(FXCollections.observableArrayList(resultadoBusqueda));

	}
	/*
	 * @FXML private String infoDetalles() {
	 * 
	 * }
	 */
	// lanzar formulario

	@FXML
	public void lanzarVentanaCrearPlan(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/formularioCurso.fxml"));


			Parent root;
			try {
				root = fxmlLoader.load();
				Stage stage = new Stage();
				Image icon = new Image(getClass().getResourceAsStream("/vista/resources/logo.png"));
		        stage.getIcons().add(icon);
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	@FXML
	private void volverLogin(MouseEvent event) throws IOException {

		Main.setRoot("/vista/loggin");
	}

	public void lanzarVentanaInformacionDetallada() {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/informacionDetallada.fxml"));

		try {
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @FXML
    private void eliminarPlan(ActionEvent event) throws IOException {
    	 
    	String infoSeleccionada = taskList.getSelectionModel().getSelectedItem();
    	
    	SCRUDusuarios.eliminarPlan(infoSeleccionada);
    	
    	List<PlanAcademico> rellenarLista = SCRUDusuarios.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal);

    	List<String> actualizarLista = new ArrayList();
    	
    	for (PlanAcademico a : rellenarLista) {
    		
			actualizarLista.add(a.toString());
			
		}
    	
    	taskList.setItems(FXCollections.observableArrayList(actualizarLista));
    	 
    }
    
    // funcion de objetos de planes academicos para rellenar la informacion
    
    public static List<PlanAcademico> rellenarObjetoPlanes() {
    	
    	List<PlanAcademico> rellenarLista = SCRUDusuarios.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal);

    	return rellenarLista;
    	
    }




    private String obtenerColorParaItem(String item, List<PlanAcademico> listaAcademica) {
	    // Ejemplo: Devuelve colores según un criterio. 
	    // Aquí puedes usar datos de la base de datos o lógica personalizada.
	    System.out.println("entro");
		System.out.println(item);
		for (PlanAcademico a : listaAcademica) {
			if (item.equals(a.toString())) {
				
				
				return a.getColor();
				
			}
		}
	    
	    return "red";
	}
	
	
}

