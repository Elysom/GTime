package com.GTime.GTime;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.PlanAcademico;
import utilidades.SCRUDusuarios;

public class controladorUsuario implements Initializable {

    @FXML
    private TextField txtBuscar;

    @FXML
    private Button btnCrearCurso;

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
    private  CheckBox chbRutinas;
    
    @FXML
    private CheckBox chbPlanAcademico; 
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		 // Listener para el CheckBox
		chbPlanAcademico.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

            	String cursoAlumno = SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal);
            	
            	List<PlanAcademico> objetosPlanesAcademico = SCRUDusuarios.ObtenerPlanDeCursoEspecifico(cursoAlumno);
            	
            	List<String> stringPlanesAcademico = new ArrayList();
            	
            	for (PlanAcademico a : objetosPlanesAcademico) {
					stringPlanesAcademico.add(a.toString());
				}
            	
            	taskList.setItems(FXCollections.observableArrayList(stringPlanesAcademico));
            	
            } else {
                System.out.println("Rutinas desactivadas desde FXML.");
                
                taskList.setItems(FXCollections.observableArrayList(""));
            	
            }
            
        });
        
		
	} 

    // Método para manejar el texto ingresado en el campo de búsqueda
    
    
}