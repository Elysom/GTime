package GTime;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;


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
	   private TextField txtAsignatura;    // Asignatura
	    
	   @FXML 
	   private ColorPicker txtColor;       // Color del plan
	    
	   @FXML 
	   private Button btnCrear;            // Botón para crear el plan

	   
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
	
}

}

