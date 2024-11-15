package com.GTime.GTime;


import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import utilidades.SCRUDusuarios;


public class controladorFormularioPlan implements Initializable{

	   @FXML 
	   private DatePicker txtFecha;        // Fecha del plan
	    
	   @FXML 
	   private TextField txtPlan;          // Nombre del plan
	    
	   @FXML 
	   private Spinner<Integer> txtHoras;  // Spinner para las horas
	    
	   @FXML 
	   private Spinner<Integer> txtMinutos; // Spinner para los minutos
	    
	   @FXML 
	   private ComboBox<String> txtTipo;   // ComboBox para el tipo
	    
	   @FXML 
	   private ComboBox<String> txtCurso;  // ComboBox para el curso
	    
	   @FXML 
	   private ComboBox<String> txtAsignatura;    // Asignatura
	    
	   @FXML 
	   private ColorPicker txtColor;       // Color del plan
	    
	   @FXML 
	   private Button btnCrear;            // Botón para crear el plan

	   @FXML
	   private TextField txtDescripcion;
	   
	   @FXML
	   private Label txtValidacion;
	   
	   
	   
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	// texto para poner los cursos
	txtCurso.setItems(FXCollections.observableArrayList("1.º SMR","2.º SMR","1.º DAM","2.º DAM"));

	// combobox para los tipos
	
	txtTipo.setItems(FXCollections.observableArrayList("Examen","Cursos","Excursion","Proyecto"));
	// Confiurar las horas del spinner tipo de 00 a 23
	
	SpinnerValueFactory<Integer> horas = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
	txtHoras.setValueFactory(horas);
	
	// Lo mismo con los minutos
	
	SpinnerValueFactory<Integer> minutos = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
	txtMinutos.setValueFactory(minutos);
	
	txtValidacion.setText(controladorLoggin.nombreUsuGlobal);
	
	// Configuracion de asignatura depende de lo seleccionado

	// Añadimos una funcion de listener a la variable combobox txtCurso
	
	txtCurso.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {  // Verificar que newValue no sea null
            if ("1.º SMR".equals(newValue)) {
                txtAsignatura.setItems(FXCollections.observableArrayList("MOMAE", "APLOF", "SIOP", "RL", "FOL"));
            } else if ("2.º SMR".equals(newValue)) {
                txtAsignatura.setItems(FXCollections.observableArrayList("SOM", "EOI", "ASO", "RL", "SEG"));
            } else if ("1.º DAM".equals(newValue)) {
                txtAsignatura.setItems(FXCollections.observableArrayList("PRO", "BD", "LMSGI", "ED", "FOL"));
            } else if ("2.º DAM".equals(newValue)) {
                txtAsignatura.setItems(FXCollections.observableArrayList("PSP", "AD", "PMDM", "SGE", "EIE"));
            } else {
                txtAsignatura.setItems(FXCollections.observableArrayList());  // Limpiar si no hay curso seleccionado
            }
        }
    });
	
	
	
}
	@FXML
	public void crearPlan(ActionEvent event)  {
		
		SCRUDusuarios.agregarPlan(controladorLoggin.nombreUsuGlobal, txtPlan.getText(), obtenerFechaHoraSQL(), txtTipo.getValue(), txtCurso.getValue(), txtAsignatura.getValue(), transformarColor(), txtDescripcion.getText());
		
	}
	
	@FXML
	public Timestamp obtenerFechaHoraSQL() {
		
		// Obtenemos la fecha y hora y lo convertimos en LocalDataTime
		
				LocalDate fecha = txtFecha.getValue();
				
				int horas = txtHoras.getValue();
				
				int minutos = txtMinutos.getValue();
				
				LocalDateTime fechaHorayMinutos = LocalDateTime.of(fecha, LocalTime.of(horas, minutos));
				
				return Timestamp.valueOf(fechaHorayMinutos);
				
	}
	
	@FXML
	public String transformarColor() {
		
		Color colorSeleccionado = txtColor.getValue();
		
		String colorHex = String.format("#%02X%02X%02X", (int)(colorSeleccionado.getRed() * 255), 
		                                (int)(colorSeleccionado.getGreen() * 255), 
		                                (int)(colorSeleccionado.getBlue() * 255));
		
		return colorHex;

	}

}

