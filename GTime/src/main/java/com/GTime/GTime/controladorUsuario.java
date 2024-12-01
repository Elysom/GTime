package com.GTime.GTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.PlanAcademico;
import modelo.Rutina;
import modelo.Tarea;
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

    @FXML  CheckBox chbTareas;
    
    @FXML CheckBox chbPlanAcademico; 
    
    public static String infoSeleccionada;
    
    private controladorFormularioTarea controladorTarea;
    
    public static int contadorAux;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
                     String color = obtenerColorParaItem(item, SCRUDusuarios.rellenarTareaUsuEspecifico(), SCRUDusuarios.rellenarRutinaUsuEspecifico(), SCRUDusuarios.ObtenerPlanDeCursoEspecifico(SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal)));

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
	
	// Lanza la ventana Formulario
	@FXML
	public void lanzarVentanaFormularioTarea(ActionEvent event) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/formularioTareas.fxml"));
    	
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
		
		String cursoAlumnado =  SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal);
		
		List<String> ambasOpciones = new ArrayList();
		
		List<Tarea> listaPooTarea = SCRUDusuarios.rellenarTareaUsuEspecifico();
		List<PlanAcademico> objetosPlanesAcademico = SCRUDusuarios.ObtenerPlanDeCursoEspecifico(cursoAlumnado);
    
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
    	
    	List<PlanAcademico> objetosPlanesAcademico = SCRUDusuarios.ObtenerPlanDeCursoEspecifico(cursoAlumno);
    	
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
			
    		List<PlanAcademico> ejecutarBusqueda = SCRUDusuarios.ObtenerPlanDeCursoEspecifico(cursoAlumno);
        	 	
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
			
    		List<PlanAcademico> ejecutarBusqueda = SCRUDusuarios.ObtenerPlanDeCursoEspecifico(cursoAlumno);
    		
        	 	
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
	    
	    return "";
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