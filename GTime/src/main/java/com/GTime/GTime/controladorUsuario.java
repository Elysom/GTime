package com.GTime.GTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
import utilidades.SCRUDusuarios;

public class controladorUsuario implements Initializable {

	@FXML
	private TextField txtBuscar;

	@FXML
	private Button btnCrearTarea;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button atrasMes;

	@FXML
	private Button alanteMes;

	@FXML
	private Text year;

	@FXML
	private Text month;


    @FXML ListView<String> taskList;

	@FXML
	private GridPane calendarGrid;

	@FXML
	private Label lblNUsuario;

	@FXML
	private CheckBox chbTareas;

	@FXML
	private CheckBox chbPlanAcademico;
	CalendarioDias calendarioDias;

	private CalendarioMetodos calendarioMetodos;

	public List<String> colores = new ArrayList<>();

	public List<String> listaTareas;
    

    public static String infoSeleccionada;
    
    private controladorFormularioTarea controladorTarea;
    
    public static int contadorAux;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		lblNUsuario.setText(controladorLoggin.nombreUsuGlobal);
		calendarioMetodos = new CalendarioMetodos();

		updateCalendar();

		listaTareas = agregarPlanesLista();

		taskList.setItems(FXCollections.observableArrayList(listaTareas));

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

		// Listener para el CheckBox
		chbPlanAcademico.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && chbTareas.isSelected()) {

				List<String> AmbasOpciones = rellenarYordenarAmbasListas();

				taskList.setItems(FXCollections.observableArrayList(AmbasOpciones));
				updateCalendar();

			} else if (newValue) {

				List<String> stringPlanesAcademico = rellenarPlanAcademicos();
				taskList.setItems(FXCollections.observableArrayList(stringPlanesAcademico));
				updateCalendar();

			} else if (newValue == false && chbTareas.isSelected()) {

				List<String> tareaDelUsu = rellenarListaTareaString();

				taskList.setItems(FXCollections.observableArrayList(tareaDelUsu));
				updateCalendar();

			} else {

				System.out.println("Rutinas desactivadas desde FXML.");

				taskList.setItems(FXCollections.observableArrayList(""));
				updateCalendar();
			}

		});

		// Listener para el checkBox de Tareas
		chbTareas.selectedProperty().addListener((observable, oldValue, newValue) -> {

			List<String> tareaDelUsu = rellenarListaTareaString();

			if (newValue == true && chbPlanAcademico.isSelected()) {

				List<String> AmbasOpciones = rellenarYordenarAmbasListas();

				taskList.setItems(FXCollections.observableArrayList(AmbasOpciones));
				updateCalendar();

			} else if (newValue) {

				taskList.setItems(FXCollections.observableArrayList(tareaDelUsu));
				updateCalendar();

			} else if (newValue == false && chbPlanAcademico.isSelected()) {

				List<String> stringPlanesAcademico = rellenarPlanAcademicos();
				taskList.setItems(FXCollections.observableArrayList(stringPlanesAcademico));
				updateCalendar();

			} else {
				taskList.setItems(FXCollections.observableArrayList(""));
				updateCalendar();

			}

		});

		 actualizarLista();

		// Listener pa buscar
		
		 txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
	            
		        buscarElemento(); // Llama a buscarElemento con el nuevo texto
		        	
		        });
		 
		 // Listener para mostrar la info plan academico
		 
		 taskList.setOnMouseClicked(event -> {
			 
     		infoSeleccionada = taskList.getSelectionModel().getSelectedItem();

	        	
	        	if (event.getClickCount() == 2) {
	        			        		  
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
		 taskList.setCellFactory(lv -> new ListCell<String>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);

                 if (empty || item == null) {
                     setText(null);
                     setStyle(""); // Estilo por defecto
                 } else {
                     setText(item);

                     // Obtener el color de fondo
                     String color = obtenerColorParaItem(item, SCRUDusuarios.rellenarTareaUsuEspecifico(), SCRUDusuarios.rellenarRutinaUsuEspecifico(), SCRUDusuarios.obtenerPlanDeCursoEspecifico(SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal)));

                     // Validar si el color es válido
                     if (color == null || color.isEmpty()) {
                         color = "green";  // Color por defecto
                     }

                     // Aplicar el estilo con el color
                     setStyle("-fx-background-color: " + color + ";");
                 }
             }
         });
		 
		
		 //controladorFormularioTarea ejemplo = new controladorFormularioTarea();
		
		 


	}

	@FXML
	private void previousMonth() {
		calendarioMetodos.previousMonth();
		updateCalendar();
	}

	@FXML
	private void nextMonth() {
		calendarioMetodos.nextMonth();
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

				if (chbTareas.isSelected()) {
					if (items < 4) {
						List<Rutina> listaRutinas = SCRUDusuarios.rellenarRutinaUsuEspecifico();
						for (Rutina ru : listaRutinas) {
							if (CalendarioDias.getDiaSemana(day.getDate()).equals(ru.getDiaSemana())) {
								Label nLabel = new Label("R-" + ru.getNombreTarea());
								
								nLabel.setOnMouseClicked(event -> {
									infoSeleccionada = ru.toString();
									lanzarVentanaInformacionDetallada();
								});
								
								dayGrid.add(nLabel, 0, colPlan++);

							}
						}
					}
				}
				if (chbPlanAcademico.isSelected()) {
					if (items < 4) {
						List<PlanAcademico> listaplanes = SCRUDusuarios.obtenerPlanDeCursoEspecifico(
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
				}
				if (chbTareas.isSelected()) {
					if (items < 4) {
						List<Tarea> listaTarea = SCRUDusuarios.rellenarTareaUsuEspecifico();
						for (Tarea ta : listaTarea) {
							if (ta.getFecha().getDayOfMonth() == Integer.parseInt(day.getDisplayText())
									&& ta.getFecha().getMonthValue() == currentMonth.getMonthValue()
									&& ta.getFecha().getYear() == currentMonth.getYear()) {
								Label ntareaLabel = new Label(contador + "." + ta.getNombreTarea());
								ntareaLabel.setOnMouseClicked(event -> {
									infoSeleccionada = ta.toString();
									lanzarVentanaInformacionDetallada();
								});
								dayGrid.add(ntareaLabel, 0, colPlan++);
								contador++;
							}

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
				if(items>=4) {
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
			column.setPercentWidth(100.0 / 6); // Cada columna ocupa el 15% aprox del espacio disponible
			calendarGrid.getColumnConstraints().add(column);
		}

		// Configura las restricciones para las filas (6 filas para los días)
		// Hay un máximo de 6 filas por mes
		for (int i = 0; i < 6; i++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(100.0 / 7); // Cada fila ocupa el 16.67% del espacio disponible
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
	private void eliminarPlan(ActionEvent event) throws IOException {

		String infoSeleccionada = taskList.getSelectionModel().getSelectedItem();

		SCRUDusuarios.eliminarPlan(infoSeleccionada);

		List<PlanAcademico> rellenarLista = SCRUDusuarios
				.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal);

		List<String> actualizarLista = new ArrayList();

		for (PlanAcademico a : rellenarLista) {

			actualizarLista.add(a.toString());

		}

		taskList.setItems(FXCollections.observableArrayList(actualizarLista));

	}

		 

        
		 
		
	
	

	@FXML
	public void lanzarVentanaFormularioTarea(ActionEvent event) {

		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/formularioTareas.fxml"));

        try {
        	Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            
         // Obtener la instancia del controladorFormularioTarea y pasar el controladorUsuario
            controladorFormularioTarea controladorTarea = fxmlLoader.getController();
            controladorTarea.setControladorUsuario(this);  // Establecer el controladorUsuario
            
            stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	
	// Devuelve una lista String los objetos de lista toString
	@FXML 
	public static List<String> rellenarListaTareaString() {

		List<Rutina> listaPooRutina = SCRUDusuarios.rellenarRutinaUsuEspecifico();

		List<Tarea> listaPooTarea = SCRUDusuarios.rellenarTareaUsuEspecifico();

		List<String> listaTareaString = new ArrayList();

		for (Rutina a : listaPooRutina) {

			listaTareaString.add(a.toString());
		}

		for (Tarea a : listaPooTarea) {

			listaTareaString.add((String) a.toString());
		}

		return listaTareaString;

	}

	// Método para hacer ambas lista

	public static List<String> rellenarYordenarAmbasListas() {

		String cursoAlumnado = SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal);

		List<String> ambasOpciones = new ArrayList();

		List<Tarea> listaPooTarea = SCRUDusuarios.rellenarTareaUsuEspecifico();
		List<PlanAcademico> objetosPlanesAcademico = SCRUDusuarios.obtenerPlanDeCursoEspecifico(cursoAlumnado);

		List<Rutina> listaPooRutina = SCRUDusuarios.rellenarRutinaUsuEspecifico();

		List<LocalDateTime> listaFechas = new ArrayList();

		// Metemos todas las fechas y horas de tareas

		for (Tarea a : listaPooTarea) {

			listaFechas.add(a.getFecha());

		}

		// 2.1 Foreach para las fechas y horas plan academico

		for (PlanAcademico a : objetosPlanesAcademico) {

			listaFechas.add(a.getFechahorasPlan());

		}

		// 3 Ordenamos la fechas de manera descendente en nuestra lista auxiliar

		listaFechas.sort(Comparator.reverseOrder());

		// Primero añadimos la rutinas de los usuarios

		for (Rutina a : listaPooRutina) {

			ambasOpciones.add(a.toString());

		}

		// foreach anidado
		for (LocalDateTime a : listaFechas) {

			for (PlanAcademico b : objetosPlanesAcademico) {

				if (a.isEqual(b.getFechahorasPlan())) {

					ambasOpciones.add(b.toString());

				}
			}

			for (Tarea c : listaPooTarea) {

				if (a.isEqual(c.getFecha())) {

					ambasOpciones.add(c.toString());

				}
			}

		}

		return ambasOpciones;

	}

	public static List<String> rellenarPlanAcademicos() {
		String cursoAlumno = SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal);

		List<PlanAcademico> objetosPlanesAcademico = SCRUDusuarios.obtenerPlanDeCursoEspecifico(cursoAlumno);

		List<String> stringPlanesAcademico = new ArrayList();

		for (PlanAcademico a : objetosPlanesAcademico) {
			stringPlanesAcademico.add(a.toString());
		}

		return stringPlanesAcademico;

	}

	
	
	public void botonEliminar(ActionEvent event) {
		
		boolean verificacion = false;
		
		// Cogemos el objeto a eliminar
		
		String elemento = taskList.getSelectionModel().getSelectedItem();
		
		// Lo desoglamos
		
		//RUTINA - explotar el corte ingles _ J _ 10:00
		
		

		List<Rutina> listaRutina = utilidades.SCRUDusuarios.rellenarRutinaUsuEspecifico();
		
		List<Tarea> listaTarea = utilidades.SCRUDusuarios.rellenarTareaUsuEspecifico();
		
		
		// Buscar con foreach el elemento entre los 3 objetos
		
		for (Rutina a : listaRutina) {
			
			if (a.toString().equals(elemento)) {
				
				SCRUDusuarios.eliminarRutinas(a.getNombreTarea(), a.getDiaSemana(), a.getHora());
			
				verificacion = true;
				
			}
			
		}
		
		// Buscar con foreach si es un elemento tares
		
		for (Tarea a : listaTarea) {
			
			if (a.toString().equals(elemento)) {
				
				SCRUDusuarios.eliminarTareas(a.getFecha(), a.getNombreTarea());
				
				verificacion = true;
				
			}
		}
		
		if (verificacion) {
			// Actualizamos la tabla 
			
			List<String> tareaYrutinas = rellenarListaTareaString();
			
			taskList.setItems(FXCollections.observableArrayList(tareaYrutinas));
		}
		
		
	}
	
	
	@FXML
    private void buscarElemento() {
    	
    	System.out.println("Método buscarElemento invocado");
    	
    	String palabra_busqueda = txtBuscar.getText();
    	
    	List<String> resultadoBusqueda = new ArrayList();
    	
    	
    	if (chbPlanAcademico.isSelected() && !chbTareas.isSelected()) {
    		
    		String cursoAlumno = SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal);
			
    		List<PlanAcademico> ejecutarBusqueda = SCRUDusuarios.obtenerPlanDeCursoEspecifico(cursoAlumno);
        	 	
        	for (PlanAcademico aux: ejecutarBusqueda) {
    			
        		if (aux.getNombrePlan().toLowerCase().contains(palabra_busqueda) || aux.getNombrePlan().contains(palabra_busqueda)) {
    				
        			resultadoBusqueda.add(aux.toString());
    				
    			}
    		
		}	
    	
		}
    	
    	if (!chbPlanAcademico.isSelected() && chbTareas.isSelected()) {
    		
    		List<Rutina> ejecutarBusqueda1 = SCRUDusuarios.rellenarRutinaUsuEspecifico();
    					
    		List<Tarea> ejecutarBusqueda2 = SCRUDusuarios.rellenarTareaUsuEspecifico();
    		
        	 	
        	for (Rutina aux: ejecutarBusqueda1) {
    			
        		if (aux.getNombreTarea().toLowerCase().contains(palabra_busqueda) || aux.getNombreTarea().contains(palabra_busqueda)) {
    				
        			resultadoBusqueda.add(aux.toString());
    				
    			}
    		
		}
        	
        	for (Tarea aux : ejecutarBusqueda2) {
				
        		if (aux.getNombreTarea().toLowerCase().contains(palabra_busqueda) || aux.getNombreTarea().contains(palabra_busqueda)) {
    				
        			resultadoBusqueda.add(aux.toString());
    				
    			}
			}
        	
        	
    	
		}
    	
    	if (chbPlanAcademico.isSelected() && chbTareas.isSelected()) {
			
    		List<Rutina> ejecutarBusqueda1 = SCRUDusuarios.rellenarRutinaUsuEspecifico();
			
    		List<Tarea> ejecutarBusqueda2 = SCRUDusuarios.rellenarTareaUsuEspecifico();
    		
    		String cursoAlumno = SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal);
			
    		List<PlanAcademico> ejecutarBusqueda = SCRUDusuarios.obtenerPlanDeCursoEspecifico(cursoAlumno);
    		
        	 	
        	for (Rutina aux: ejecutarBusqueda1) {
    			
        		if (aux.getNombreTarea().toLowerCase().contains(palabra_busqueda) || aux.getNombreTarea().contains(palabra_busqueda)) {
    				
        			resultadoBusqueda.add(aux.toString());
    				
    			}
    		
		}
        	
        	for (Tarea aux : ejecutarBusqueda2) {
				
        		if (aux.getNombreTarea().toLowerCase().contains(palabra_busqueda) || aux.getNombreTarea().contains(palabra_busqueda)) {
    				
        			resultadoBusqueda.add(aux.toString());
    				
    			}
			}
        	
        	 	
        	for (PlanAcademico aux: ejecutarBusqueda) {
    			
        		if (aux.getNombrePlan().toLowerCase().contains(palabra_busqueda) || aux.getNombrePlan().contains(palabra_busqueda)) {
    				
        			resultadoBusqueda.add(aux.toString());
    				
    			}
    		
		}
        	
        	
    		
		}
    	
    	
    	
    	
        taskList.setItems(FXCollections.observableArrayList(resultadoBusqueda));
    	
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
	private void volverLogin(MouseEvent event) throws IOException {
		
		
		Main.setRoot("/vista/loggin");
	}

	
	
	private String obtenerColorParaItem(String item, List<Tarea> listaTareas, List<Rutina> listaRutina, List<PlanAcademico> listaPlanAcademico) {
	    // Ejemplo: Devuelve colores según un criterio. 
	    // Aquí puedes usar datos de la base de datos o lógica personalizada.
	    System.out.println("entro");
		System.out.println(item);
		for (Tarea a : listaTareas) {
			if (item.equals(a.toString())) {
				
				
				return a.getColor();
				
			}
		}
	    
	    for (Rutina a : listaRutina) {
			if (item.equals(a.toString())) {
				return a.getColor();
			}
		}
	    
	    for (PlanAcademico a : listaPlanAcademico) {
			if (item.equals(a.toString())) {
				return a.getColor();
			}
		}
	    
	    return "white";
	}
	
    public void actualizarLista() {
    	

    	
    	
    	// Listener para el CheckBox
    			chbPlanAcademico.selectedProperty().addListener((observable, oldValue, newValue) -> {
    	            if (newValue && chbTareas.isSelected()) {

    	            List<String> AmbasOpciones = rellenarYordenarAmbasListas();
    	            
    	            taskList.setItems(FXCollections.observableArrayList(AmbasOpciones));
    	            	
    	            
    	            } else if (newValue){
    	                
    	            	List<String> stringPlanesAcademico = rellenarPlanAcademicos();
    	            	taskList.setItems(FXCollections.observableArrayList(stringPlanesAcademico));

    	            	
    	            } else if (newValue == false && chbTareas.isSelected()) {
    	                List<String> tareaDelUsu = rellenarListaTareaString();
    	                
    	                taskList.setItems(FXCollections.observableArrayList(tareaDelUsu));

    	                
    	            } else {
    	            	
    	            	System.out.println("Rutinas desactivadas desde FXML.");
    	                
    	                taskList.setItems(FXCollections.observableArrayList(""));
    	                
    	            }
    	            
    	            
    	            
    	        });
    			
    			// Listener para el checkBox de Tareas
    			chbTareas.selectedProperty().addListener((observable, oldValue, newValue) -> {
    				
    				List<String> tareaDelUsu = rellenarListaTareaString();
    				
    				if (newValue == true && chbPlanAcademico.isSelected()) {
    					
    		            List<String> AmbasOpciones = rellenarYordenarAmbasListas();
    					
    					taskList.setItems(FXCollections.observableArrayList(AmbasOpciones));
    					
    				} else if (newValue){
    					
    					taskList.setItems(FXCollections.observableArrayList(tareaDelUsu));
    					
    				} else if(newValue == false && chbPlanAcademico.isSelected()) {
    					
    					List<String> stringPlanesAcademico = rellenarPlanAcademicos();
    	            	taskList.setItems(FXCollections.observableArrayList(stringPlanesAcademico));

    				} else {
    					taskList.setItems(FXCollections.observableArrayList(""));

    				}
    				
    			});
    			
    			
    			if (chbPlanAcademico.isSelected() && chbTareas.isSelected()) {
					
    				 List<String> AmbasOpciones = rellenarYordenarAmbasListas();
     	            
     	            taskList.setItems(FXCollections.observableArrayList(AmbasOpciones));
     	            
				}
    			
    			
    }

}