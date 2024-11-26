package com.GTime.GTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.PlanAcademico;
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

    @FXML
    private ListView<String> taskList;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Label lblNUsuario;

    @FXML
    private  CheckBox chbTareas;
    
    @FXML
    private CheckBox chbPlanAcademico; 
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		 // Listener para el CheckBox
		chbPlanAcademico.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && chbTareas.isSelected()) {

            List<String> AmbasOpciones = rellenarYordenarAmbasListas();
            
            taskList.setItems(FXCollections.observableArrayList(AmbasOpciones));
            	
            } else if (newValue){
                
            	List<String> stringPlanesAcademico = rellenarPlanAcademicos();
            	taskList.setItems(FXCollections.observableArrayList(stringPlanesAcademico));

            	
            } else if (newValue == false && chbTareas.isSelected() ) {
            	
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
		
		
        
		
	} 
	
	@FXML
	public void lanzarVentanaFormularioTarea(ActionEvent event) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/formularioTareas.fxml"));
    	
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
	public static List<String> rellenarListaTareaString() {
		
		List<Tarea> listaPooTarea = SCRUDusuarios.rellenarTareaUsuEspecifico();
		
		List<String> listaTareaString = new ArrayList();
		
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
		
		List<LocalDateTime> listaFechas = new ArrayList();
		
		List<PlanAcademico> objetosPlanesAcademico = SCRUDusuarios.ObtenerPlanDeCursoEspecifico(cursoAlumnado);
    
		
    	LocalDateTime fechaAux;
    	
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
	
	
    
    
}